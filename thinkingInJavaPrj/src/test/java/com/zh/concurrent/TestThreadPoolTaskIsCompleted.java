package com.zh.concurrent;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolTaskIsCompleted {

    private ThreadPoolExecutor threadPoolExecutor;

    @Before
    public void before() {
        threadPoolExecutor = new ThreadPoolExecutor(10,
                50,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10));
    }

    @Test
    public void testCompletedCount() throws InterruptedException {
        for (int i = 0 ; i < 10 ; i++) {
            final int sleepSecond = i;
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(sleepSecond);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolExecutor.shutdown();
        System.out.println(threadPoolExecutor.getActiveCount());
        System.out.println(threadPoolExecutor.getCompletedTaskCount());

        threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);

        threadPoolExecutor.shutdown();
        System.out.println(threadPoolExecutor.getActiveCount());
        System.out.println(threadPoolExecutor.getCompletedTaskCount());
    }
}
