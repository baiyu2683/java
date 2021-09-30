package com.zh.curator.lock;

import com.zh.curator.ClientSample;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DistDoubleBarrierSample {

    private static final String BARRIER = "/barrier";
    static int memberCount = 10;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0 ; i < memberCount ; i++) {
            new Thread(() -> {
                try {
                    CuratorFramework client = ClientSample.client();
                    client.start();
                    DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, BARRIER, memberCount);
                    System.out.println("线程: " + Thread.currentThread().getName() + " enter");
                    barrier.enter();
                    System.out.println("启动任务..." + Thread.currentThread().getName());
                    barrier.leave();
                    System.out.println("线程: " + Thread.currentThread().getName() + " leave");
                } catch (Exception e) {}
            }).start();
        }
        new CountDownLatch(1).await();
    }
}
