package com.zh.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.shaded.com.google.common.cache.Cache;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.TimeUnit;

public class DeleteNodeSample {

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
        client.getData().storingStatIn(stat).forPath("/mynode");
        System.out.println(stat.getVersion());

        // 监听删除事件
        CuratorCache cache = CuratorCache.builder(client, "/mynode").build();
        cache.start();
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forDeletes(childData -> {
                    System.out.println(childData.getStat());
                })
                .build();
        cache.listenable().addListener(listener);

        // 删除节点
        client.delete()
                .guaranteed() // 客户端会话有效期间，框架在后台持续进行删除操作直到删除成功
                .deletingChildrenIfNeeded()
                .withVersion(stat.getVersion())
                .forPath("/mynode");

        System.out.println("asdf");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

}
