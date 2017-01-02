package com.zh.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zh on 2017-01-02.
 */
public class Interrupting2 {
    public static void main(String[] args) throws Exception {
        //Blocked2在主线程中被创建，主线程获得锁
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}

class BlockedMutex {
    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        System.out.println("currentThread: " + Thread.currentThread().getName());
        lock.lock();
    }

    public void f() {
        try {
            System.out.println("currentThread: " + Thread.currentThread().getName());
            //响应中断
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
            System.out.println("Interrupted from lock acquisition in f()");
        }
    }
}

class Blocked2 implements Runnable {
    BlockedMutex blocked = new BlockedMutex();

    @Override
    public void run() {
        //Blocked2的线程调用f()试图获得blocked的锁，但是主线程main没有释放，因此blocked.f()会阻塞
        System.out.println("Waiting for f() in BlockedMutext");
        blocked.f();
        System.out.println("Broken out of blocked call");
    }
}