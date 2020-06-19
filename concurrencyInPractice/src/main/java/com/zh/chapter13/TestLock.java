package com.zh.chapter13;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zh on 2017-10-18.
 */
public class TestLock {

    @Test
    public void test1() {
        //典型Lock使用示例
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            //
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void test2() throws InterruptedException {
        Lock lock = new ReentrantLock();
        lock.lockInterruptibly();
        try {
            cancelableTask();
        } finally {
            lock.unlock();
        }
    }

    public void cancelableTask() throws InterruptedException {

    }

    private static Condition condition = new ReentrantLock().newCondition();

    public void sleepLock() {
        synchronized (this) {
            try {
                System.out.println("sleepLock : " + Thread.currentThread().getId());
                // 不会释放锁，别的线程进不来
//                TimeUnit.SECONDS.sleep(10);
                // 会释放锁，别的线程可以进来, 必须在synchronized中使用
//                this.wait(10 * 1000);
                // 报错，需要先lock.lock()
//                condition.await(10, TimeUnit.SECONDS);
                // 不会释放锁，别的线程进不来
                LockSupport.park();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("sleepLock finally : " + Thread.currentThread().getId());
            }
        }
    }

    @Test
    public void testSleepLock() {
        TestLock testLock = new TestLock();
        for (int i = 0 ; i < 10 ; i++) {
            new Thread(() -> {
                Thread.yield();
                testLock.sleepLock();
            }).start();
        }
        LockSupport.park();
    }
}
