package com.zh.chapter2;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicTest {

    public static void main(String[] args) {
        String s = "100";
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<String>(s, 1);
        AtomicReference<String> atomicReference = new AtomicReference<>();
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
    }
}
