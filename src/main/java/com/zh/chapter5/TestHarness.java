package com.zh.chapter5;

import java.util.concurrent.CountDownLatch;

/**
 * 利用CountDownLatch控制任务启动和计算任务执行时间
 * Created by zh on 2017-07-30.
 */
public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        //起始门和结束门
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for(int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {
                    }
            });
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();   //所有线程都准备好了，打开起始门，线程开始执行
        endGate.await();         //等待所有线程执行完毕
        long end = System.nanoTime();
        return end - start;
    }
}
