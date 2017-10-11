package com.zh.chapter8;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-10-11.
 */
public class TestExecutor {

    public static void main(String[] args) {
        TimingThreadPool timingThreadPool = new TimingThreadPool(10, 15, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        timingThreadPool.execute(() -> {
            System.out.println("asdf");
        });
    }
}
