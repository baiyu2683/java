package com.zh.singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainEntry {

    public static void main(String[] args) {
        int threadCount = 500;
        CountDownLatch latch = new CountDownLatch(threadCount);
        CountDownLatch start = new CountDownLatch(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1000,
                1000,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50));
        for (int i = 0 ; i < threadCount ; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    start.await();
                    Singleton singleton = Singleton.getInstance();
                    System.out.println(singleton.getVolatileCheck());
                } catch (InterruptedException e) {
                  e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        start.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdownNow();
        }
        System.out.println(Singleton.counter());
    }
}
