package com.zh.chapter10;

import java.net.HttpRetryException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zh on 2017-10-12.
 */
public class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            Thread.yield();
            synchronized (right) {
                System.out.println("leftRight running");
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            Thread.yield();
            synchronized (left) {
                System.out.println("rightLeft running");
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        LeftRightDeadlock leftRightDeadlock = new LeftRightDeadlock();
        Thread thread1 = new Thread(() -> leftRightDeadlock.leftRight());
        Thread thread2 = new Thread(() -> leftRightDeadlock.rightLeft());
        thread1.start();
        thread2.start();
    }
}
