package com.zh.singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainEntry {

    public static void main(String[] args) {
        int threadCount = 10;
        CountDownLatch latch = new CountDownLatch(threadCount);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                20,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50));
        for (int i = 0 ; i < threadCount ; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    System.out.println(Singleton.getInstance());
                } finally {
                    latch.countDown();
                }
            });
        }
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
