package com.zh;

import org.apache.zookeeper.*;
import org.apache.zookeeper.audit.AuditEvent;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZkCliDemo {

    private static CountDownLatch connectedLatch = new CountDownLatch(1);
    private static String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    private static ZooKeeper zooKeeper;

    public ZooKeeper zkCli() throws IOException {
        zooKeeper = new ZooKeeper(connectString,
                5000, new MyWatcher());
        System.out.println(zooKeeper.getState());
        try {
            connectedLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper链接已建立");
        return zooKeeper;
    }

    private class MyWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            System.out.println("receive event: " + event);
            if (Event.KeeperState.SyncConnected == event.getState()) {
                if (Event.EventType.None == event.getType() && null == event.getPath()) {
                    // 首次链接时，解除锁
                    connectedLatch.countDown();
                } else if (Event.EventType.NodeChildrenChanged == event.getType()) {
                    // 子节点更改事件
                    try {
                        List<String> children = zooKeeper.getChildren(event.getPath(), true);
                        System.out.println("children: " + children.stream().reduce((s1, s2) -> s1 + ", " + s2));
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ZkCliDemo zkCliDemo = new ZkCliDemo();
        ZooKeeper zooKeeper = zkCliDemo.zkCli();

        // 创建节点
        // 1 创建临时节点
        String path1 = zooKeeper.create("/test-emphemeral", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        // 2 创建临时顺序节点
        String path2 = zooKeeper.create("/test-emphemeral", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(path1 + ", " + path2);

        // 3 异步创建临时节点
        zooKeeper.create("/test-emphemeral-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                (int rc, String path, Object ctx, String name, Stat stat) -> {
                    System.out.println(rc + ", " + path + ", " + ctx + ", " + name + ", " + stat);
                }, "custom context");
        // 4 异步创建临时顺序节点
        zooKeeper.create("/test-emphemeral-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,
                (int rc, String path, Object ctx, String name, Stat stat) -> {
                    System.out.println(rc + ", " + path + ", " + ctx + ", " + name + ", " + stat);
                }, "custom context");

        // 建立子节点， Watcher中会触发子节点更新事件
        zooKeeper.create("/test-emphemeral-/c1", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                (int rc, String path, Object ctx, String name, Stat stat) -> {
                    System.out.println(rc + ", " + path + ", " + ctx + ", " + name + ", " + stat);
                }, "custom context children node");

        // 删除节点
        zooKeeper.delete("/test-emphemeral", -1);
        // 第二次删除同一个节点会报resultCode会是-101，也就是nonode错误
        zooKeeper.delete("/test-emphemeral", -1,
                (int rc, String path, Object ctx) -> {
                    System.out.println(rc + ", " + path + ", " + ctx);
                }, "custom context- delete async");


        zooKeeper.close();
    }
}
