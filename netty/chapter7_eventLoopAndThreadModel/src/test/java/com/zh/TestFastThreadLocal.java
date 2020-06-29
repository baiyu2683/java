package com.zh;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestFastThreadLocal {

    private static final FastThreadLocal<String> threadLocal1 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal2 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal3 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal4 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal5 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal6 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal7 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal8 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal9 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal10 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal11 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal12 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal13 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal14 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal15 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal16 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal17 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal18 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal19 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal20 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal21 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal22 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal23 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal24 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal25 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal26 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal27 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal28 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal29 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal30 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal31 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal32 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal33 = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal34 = new FastThreadLocal<>();
    private static final ThreadLocal<String> threadLocal35 = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        int count = 1000000000;
        FastThreadLocalThread fastThreadLocalThread = new FastThreadLocalThread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 0 ; i < count ; i++) {
                threadLocal34.set("100");
                try {
                    threadLocal34.get();
                } finally {
                    threadLocal34.remove();
                }
            }
            System.out.println("fast: " + (System.currentTimeMillis() - start));
        });
        fastThreadLocalThread.start();
        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (int i = 0 ; i < count ; i++) {
                threadLocal34.set("100");
                try {
                    threadLocal34.get();
                } finally {
                    threadLocal34.remove();
                }
            }
            System.out.println("slow: " + (System.currentTimeMillis() - start));
        });
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        new CountDownLatch(1).await();
    }
}
