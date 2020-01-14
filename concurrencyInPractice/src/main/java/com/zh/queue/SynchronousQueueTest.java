package com.zh.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Author: Administrator <br/>
 * Date: 2018-07-18 <br/>
 */
public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        synchronousQueue.put("asdf");
    }
}
