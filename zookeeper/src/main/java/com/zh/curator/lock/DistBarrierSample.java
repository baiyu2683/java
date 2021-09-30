package com.zh.curator.lock;

import com.zh.curator.ClientSample;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 这个卡死在watiOnBarrier上了，暂时没有作用
 * 原因： 使用方式出错了，DistributedBarrier的waitOnBarrier需要再removeBarrier之后才继续执行
 */
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
                    barrier.setBarrier();
                    System.out.println("线程: " + Thread.currentThread().getName() + " set");
                    barrier.waitOnBarrier();
                    System.out.println("启动任务..." + Thread.currentThread().getName());
                } catch (Exception e) {}
            }).start();
        }
        TimeUnit.SECONDS.sleep(1);
        if (barrier != null) {
            try {
                System.out.println("removeBarrier...");
                barrier.removeBarrier();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new CountDownLatch(1).await();
    }
}
