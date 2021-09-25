package com.zh.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * curator允许用户传入自定义得Executor用于任务执行
 */
public class CreateNodeBackGroundSample {

    private static String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    private static Executor executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .retryPolicy(retryPolicy)
                .connectString(connectString)
                .sessionTimeoutMs(6000)   // 设置超时时间，如果超时curator会自动重连
                .build();
        client.start();

        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("eventCode: " + event.getResultCode() + "; eventType: " + event.getType());
                        System.out.println("Thread of processResult: " + Thread.currentThread().getName());
                    }
                }, executor)
                .forPath("/mynode/c1", "init".getBytes());

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

}
