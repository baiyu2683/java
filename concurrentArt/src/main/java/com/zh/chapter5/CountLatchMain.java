package com.zh.chapter5;

import java.util.concurrent.CountDownLatch;

public class CountLatchMain {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        latch.countDown();
    }
}
