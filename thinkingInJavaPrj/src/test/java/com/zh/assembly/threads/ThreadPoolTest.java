package com.zh.assembly.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName());
            e.printStackTrace();
        });
        Thread thread = new Thread(() -> {
            throw new RuntimeException("hehe");
        });
        thread.start();
        TimeUnit.SECONDS.sleep(10);
    }
}
