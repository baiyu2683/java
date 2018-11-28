package com.zh;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class TestSemaphore {

    private static Semaphore semaphore = new Semaphore(10);

    public static void acquire() throws InterruptedException {
        semaphore.acquire();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try {
                acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();
        latch.await();
        System.out.println(semaphore.availablePermits());
    }
}
