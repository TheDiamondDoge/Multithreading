package com.returnofintelligence.hometask.core;

import com.returnofintelligence.hometask.core.concurrency.ExecutorServiceForInput;
import com.returnofintelligence.hometask.core.concurrency.WatchServiceForInput;
import com.returnofintelligence.hometask.core.utils.GetProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by The Diamond Doge on 18.11.2017.
 *
 * Creating threads for WatchService and file processing algorithm
 * Waiting for 'stop' command to shutdown the application
 */
public class Application {

    private final String STOP_COMMAND = "stop";

    private Path path;
    private BlockingQueue<Path> queue;
    private String inDirectory;
    private String outDirectory;

    public void applicationStart() {

        queue = new ArrayBlockingQueue(512);
        GetProperties properties = new GetProperties();

        inDirectory = properties.getInDirectory();
        outDirectory = properties.getOutDirectory();
        path = Paths.get(inDirectory);
        fillQueue(queue);

        Thread watchServiceThread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {

                        new WatchServiceForInput(path, queue).processEvents();
                    }
                } catch (RuntimeException e) {
                    throw new RuntimeException(Thread.currentThread().getName() + "oops!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        watchServiceThread.start();

        Thread executorServiceThread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!Thread.currentThread().isInterrupted()) {
                    ExecutorService processingThreads = Executors.newFixedThreadPool(10);
                    ExecutorServiceForInput executorServiceForInpit = new ExecutorServiceForInput(processingThreads, queue);
                    while (true) {
                        if (queue.size() != 0) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            executorServiceForInpit.execute(inDirectory, outDirectory);
                        }
                    }
                }
                throw new RuntimeException(Thread.currentThread().getName() + "oops!");
            }
        });

        executorServiceThread.start();

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Waiting for 'stop' command...");
            String input = in.nextLine();

            if (input.equals(STOP_COMMAND)) {
                watchServiceThread.interrupt();
                executorServiceThread.interrupt();
                System.exit(0);
                break;
            }
        }
    }

    public void fillQueue(BlockingQueue<Path> queue) {
        try {
            Files.walk(path)
                    .filter(Files::isRegularFile)
                    .forEach(queue::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
