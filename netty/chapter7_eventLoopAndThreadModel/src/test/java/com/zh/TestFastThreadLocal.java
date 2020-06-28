package com.zh;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.concurrent.CountDownLatch;

public class TestFastThreadLocal {

    private static final FastThreadLocal<String> threadLocal = new FastThreadLocal<>();
    private static final FastThreadLocal<String> threadLocal1 = new FastThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        FastThreadLocalThread threadLocalThread = new FastThreadLocalThread() {
            public void run() {
                threadLocal.set("100");
                threadLocal1.set("111");
                try {
                    System.out.println(threadLocal.get());
                    System.out.println("asdf");
                } finally {
                    threadLocal.remove();
                }
            }
        };
        threadLocalThread.start();
        new CountDownLatch(1).await();
    }
}
