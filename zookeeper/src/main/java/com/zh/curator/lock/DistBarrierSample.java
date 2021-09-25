package com.zh.curator.lock;

import com.zh.curator.ClientSample;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DistBarrierSample {

    private static final String BARRIER = "/barrier";
    static DistributedBarrier barrier;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0 ; i < 1 ; i++) {
            new Thread(() -> {
                try {
                    CuratorFramework client = ClientSample.client();
                    client.start();
                    barrier = new DistributedBarrier(client, BARRIER);
                    TimeUnit.SECONDS.sleep(1);
                    barrier.setBarrier();
                    System.out.println("set: " + Thread.currentThread().getName());
                    barrier.waitOnBarrier();
                    System.out.println("启动..." + Thread.currentThread().getName());
                } catch (Exception e) {}
            }).start();
        }
        new CountDownLatch(1).await();
    }
}
