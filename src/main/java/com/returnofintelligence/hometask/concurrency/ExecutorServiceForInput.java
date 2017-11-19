package com.returnofintelligence.hometask.concurrency;

import com.returnofintelligence.hometask.MethodOrder;

import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by The Diamond Doge on 19.11.2017.
 */

public class ExecutorServiceForInput{

    private ExecutorService executorService;
    private BlockingQueue<Path> queue;

    public ExecutorServiceForInput(ExecutorService executorService, BlockingQueue<Path> queue) {
        this.executorService = executorService;
        this.queue = queue;
    }

    public void execute() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    new MethodOrder().call(queue.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(Thread.currentThread().getName() + "oops!");
                }
            }
        });
    }
}


