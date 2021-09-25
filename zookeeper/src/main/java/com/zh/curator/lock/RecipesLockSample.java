package com.zh.curator.lock;

import com.zh.curator.ClientSample;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RecipesLockSample {

    private static final String LOCK_PATH = "/lock";

    public static void main(String[] args) throws InterruptedException {
        CuratorFramework client = ClientSample.client;
        client.start();

        final InterProcessMutex lock = new InterProcessMutex(client, LOCK_PATH);
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0 ; i < 30 ; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    lock.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
                String orderNo = simpleDateFormat.format(new Date());
                System.out.println("生成订单号: " + orderNo);
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        latch.countDown();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
