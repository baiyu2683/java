package com.zh.chapter13;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
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

    public void cancelableTask() throws InterruptedException {}
}
