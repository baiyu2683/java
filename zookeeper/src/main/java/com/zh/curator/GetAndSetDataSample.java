package com.zh.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.TimeUnit;

public class GetAndSetDataSample {

    private static String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";

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
                .forPath("/mynode/c1", "init".getBytes());

        Stat stat = new Stat();
        byte[] data = client.getData().storingStatIn(stat).forPath("/mynode/c1");
        System.out.println("数据: " + new String(data));
        System.out.println("stat: " + stat);

        stat = client.setData().withVersion(stat.getVersion())
                        .forPath("/mynode/c1", "新数据".getBytes());
        System.out.println("stat: " + stat);

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

}
