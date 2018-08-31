package com.zh.concurrent.exception;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 线程异常捕获，线程池异常捕获
 * Created by zh on 2017-12-22.
 */
public class TestExceptionCaught {
    public static void main(String[] args) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler((r, t) -> {
            System.out.println("123");
        });
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1) {
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Future<?> f = (Future<?>) r;
                try {
                    if (f.isDone())
                        f.get();
                } catch (Exception e) {
                    System.out.println("asdf");
                }
            }
        };
        scheduledThreadPoolExecutor.submit(() -> {
            System.out.println(1);
            throw new RuntimeException("123");
        });
        new Thread(() -> {
            System.out.println("2");
            throw new RuntimeException("456");
        }).start();
        System.in.read();
    }
}
