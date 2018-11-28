package com.zh.chapter5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 利用CountDownLatch控制任务启动和计算任务执行时间
 * Created by zh on 2017-07-30.
 */
public class TestHarness {

    public static void main(String[] args) throws InterruptedException {
        TestHarness testHarness = new TestHarness();
        long total = testHarness.timeTasks(10, () -> {
            try {
                long time = Math.round(Math.random() * 10);
                TimeUnit.SECONDS.sleep(time);
                System.out.println(Thread.currentThread().getName() + ": " + time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(total);
    }

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        //起始门和结束门
        final CountDownLatch startGate = new CountDownLatch(1);
//        final CyclicBarrier barrier = new CyclicBarrier(nThreads);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for(int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
//                    barrier.await();
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                }
            });
            t.start();
        }

        long start = System.currentTimeMillis();
        startGate.countDown();   //所有线程都准备好了，打开起始门，线程开始执行
        endGate.await();         //等待所有线程执行完毕
        long end = System.currentTimeMillis();
        return end - start;
    }
}
