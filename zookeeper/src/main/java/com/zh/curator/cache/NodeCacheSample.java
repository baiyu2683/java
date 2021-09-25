package com.zh.curator.cache;

import com.zh.curator.ClientSample;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

public class NodeCacheSample {

    private static String nodeCachePath = "/zk-book/nodecache";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = ClientSample.client;
        client.start();

        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(nodeCachePath, "init".getBytes());

        // 监听已有节点
        // 也可以监听不存在的节点，当节点创建时会触发create监听
        final CuratorCache cache = CuratorCache.builder(client, nodeCachePath).build();
        cache.start();
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forChanges((oldNode, node) -> {
                    System.out.println("changes: oldData: " + new String(oldNode.getData()) + ", newData: " + new String(node.getData()));
                })
                .forCreates(childData -> {
                    System.out.println("create: path:" + childData.getPath() + ", data: " + new String(childData.getData()));
                })
                .forDeletes(childData -> {
                    System.out.println("delete: path: " + childData.getPath());
                })
                .forInitialized(() -> {
                    System.out.println("initialized...");
                })
//                .forNodeCache(new NodeCacheListener() {
//                    @Override
//                    public void nodeChanged() throws Exception {
//                        ChildData childData = cache.get(nodeCachePath).get();
//                        System.out.println("Node data update, new data: " + new String(childData.getData()));
//                    }
//                })
                .build();
        cache.listenable().addListener(listener);

//        client.create()
//                .creatingParentsIfNeeded()
//                .withMode(CreateMode.EPHEMERAL)
//                .forPath(nodeCachePath, "init".getBytes());

        client.setData().forPath(nodeCachePath, "u".getBytes());
        TimeUnit.SECONDS.sleep(1);
        client.setData().forPath(nodeCachePath, "100".getBytes());
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(nodeCachePath + "/subnode", "subnode".getBytes());
        client.delete().deletingChildrenIfNeeded().forPath(nodeCachePath);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

}
