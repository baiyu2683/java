package com.zh.chapter15;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * synchronized不能同时被两个线程获得，可以被同一个线程多次获得
 * Created by zh on 2017-11-09.
 */
public class TestSyn {
    public synchronized void test1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(5);
    }

    public static void main(String[] args) throws IOException {
        TestSyn testSyn = new TestSyn();
        Thread t1 = new Thread(() -> {
            try {
                testSyn.test1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                testSyn.test1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        System.in.read();
    }
}
