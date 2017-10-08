package com.zh.chapter8;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by zh on 2017-10-08.
 */
public class BoundedExecutor {
    private final Executor exec;
    private final Semaphore semaphore;
    public BoundedExecutor(Executor exec, int bound) {
        this.exec = exec;
        this.semaphore = new Semaphore(bound);
    }

    public void submitTask(final Runnable command) throws InterruptedException {
//        semaphore.acquire();
//        try {
            exec.execute(() -> {
                try {
                    command.run();
                } finally {
//                    semaphore.release();
                }
            });
//        } catch (RejectedExecutionException e) {
//            semaphore.release();
//        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                15, 0l, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(2));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        BoundedExecutor boundedExecutor = new BoundedExecutor(executor, 17);
        for(int i = 0; i < 100; i++) {
            boundedExecutor.submitTask(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                System.out.println("thread:" + Thread.currentThread().getName());
            });
        }
        System.in.read();
    }
}
