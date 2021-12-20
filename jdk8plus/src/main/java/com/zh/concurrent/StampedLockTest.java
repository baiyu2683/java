package com.zh.concurrent;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {

    @Test
    public void testRead() {
        StampedLock lock = new StampedLock();
        long wl = lock.writeLock();
        System.out.printf("1");
//        lock.unlockWrite(wl);
        lock.readLock();
        System.out.printf("2");
    }
}
