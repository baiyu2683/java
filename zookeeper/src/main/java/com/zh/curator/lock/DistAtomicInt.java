package com.zh.curator.lock;

import com.zh.curator.ClientSample;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

public class DistAtomicInt {

    private static final String COUNTER_PATH = "/customer_counter";

    public static void main(String[] args) throws InterruptedException {
        CuratorFramework client = ClientSample.client;
        client.start();
        DistributedAtomicInteger distributedAtomicInteger = new DistributedAtomicInteger(client, COUNTER_PATH, new ExponentialBackoffRetry(1000, 3));
        try {
            for (;;) {
                AtomicValue<Integer> integerAtomicValue = distributedAtomicInteger.add(1);
                if (integerAtomicValue.succeeded()) {
                    System.out.println(integerAtomicValue.succeeded() + ", " + integerAtomicValue.preValue() + ", " + integerAtomicValue.postValue());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new CountDownLatch(1).await();
    }
}
