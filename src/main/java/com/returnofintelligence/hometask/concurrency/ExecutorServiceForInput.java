package com.returnofintelligence.hometask.concurrency;

import com.returnofintelligence.hometask.MethodOrder;

import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by The Diamond Doge on 19.11.2017.
 *
 * Start processing new file in new thread
 */

public class ExecutorServiceForInput{

    private ExecutorService executorService;
    private BlockingQueue<Path> queue;

    public ExecutorServiceForInput(ExecutorService executorService, BlockingQueue<Path> queue) {
        this.executorService = executorService;
        this.queue = queue;
    }

    public void execute(String inDirectory, String outDirectory) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    if(queue.size() != 0) {
                        new MethodOrder(outDirectory, inDirectory).call(queue.take());
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(Thread.currentThread().getName() + "terminated!");
                }
            }
        });
    }
}


