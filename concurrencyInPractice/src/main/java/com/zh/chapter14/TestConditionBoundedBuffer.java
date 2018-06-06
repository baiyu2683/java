package com.zh.chapter14;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-11-08.
 */
public class TestConditionBoundedBuffer {
    public static void main(String[] args) throws IOException {
        ConditionBoundedBuffer<String> conditionBoundedBuffer = new ConditionBoundedBuffer<>();
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    conditionBoundedBuffer.put("1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(5000);
                    System.out.println(conditionBoundedBuffer.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setName("t1");t2.setName("t2");
        t1.start();t2.start();
        System.in.read();
    }
}
