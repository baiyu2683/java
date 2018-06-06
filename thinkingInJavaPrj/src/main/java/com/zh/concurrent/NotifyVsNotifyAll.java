package com.zh.concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * notify置换型处于相同锁上的线程的其中一个，
 * notifyAll唤醒所有处于相同锁上的线程，哪个首先获得锁不确定，
 * 如下Timer中调用Task的notify不会唤醒Task2的阻塞线程
 * Created by zh on 2017-01-09.
 */
public class NotifyVsNotifyAll {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++) {
            executorService.execute(new Task());
        }
        executorService.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod = true;
            @Override
            public void run() {
                if(prod) {
                    System.out.println("\nnotify() ");
                    Task.blocker.prod();
                    prod = false;
                } else {
                    System.out.printf("\nnotifyAll() ");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);
    }
}
class Blocker {
    synchronized void waitingCall() {
        try {
            while(!Thread.interrupted()) {
                wait();
                System.out.println(Thread.currentThread() + " ");
            }
        } catch (InterruptedException e) {
            // OK to exit this way
        }
    }
    synchronized void prod() {
        notify();
    }
    synchronized void prodAll() {
        notifyAll();
    }
}
class Task implements Runnable {
    static Blocker blocker = new Blocker();
    public void run() {
        blocker.waitingCall();
    }
}
class Task2 implements Runnable {
    static Blocker blocker = new Blocker();
    public void run() {
        blocker.waitingCall();
    }
}
