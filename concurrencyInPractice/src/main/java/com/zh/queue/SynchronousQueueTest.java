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
//        new Thread(() -> {
//            for(;;) {
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                    synchronousQueue.offer(Math.round(Math.random() * 10) + "");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        new Thread(() -> {
//            while (true) {
                try {
                    String s = synchronousQueue.take();
                    System.out.println(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            }
        }).start();
        TimeUnit.SECONDS.sleep(100);
    }
}
