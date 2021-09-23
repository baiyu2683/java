package com.zh;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZkCliChildren implements Watcher {

    private static CountDownLatch connectedLatch = new CountDownLatch(1);
    private static String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    private static ZooKeeper zooKeeper;
    public ZooKeeper zkCli() throws IOException {
        zooKeeper = new ZooKeeper(connectString,
                5000, new ZkCliChildren());
        System.out.println(zooKeeper.getState());
        try {
            connectedLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper链接已建立");
        return zooKeeper;
    }

    private static Stat stat = new Stat();

//    private static class MyWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            System.out.println("监听到事件: " + event);
            if (Event.KeeperState.SyncConnected == event.getState()) {
                if (Event.EventType.None == event.getType() && null == event.getPath()) {
                    // 首次链接时，解除锁
                    connectedLatch.countDown();
                } else if (Event.EventType.NodeChildrenChanged == event.getType()) {
                    // 子节点更改事件
                    try {
                        // 由于watcher仅生效一次，所以需要重新注册watcher，即将第二个参数设置为true
                        List<String> children = zooKeeper.getChildren(event.getPath(), true);
                        String str = children.stream().reduce((s1, s2) -> s1 + ", " + s2).get();
                        System.out.println("children: " + str);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (Event.EventType.NodeDataChanged == event.getType()) {
                    try {
                        String data = new String(zooKeeper.getData(event.getPath(), true, stat));
                        System.out.println("数据变更：" + data + ", stat: " + stat);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
//    }

    public static void main(String[] args) throws Exception {
        ZkCliChildren zk = new ZkCliChildren();
        ZooKeeper zooKeeper = zk.zkCli();

        String parentNode = "/test-persistent";
        Stat stat1 = zooKeeper.exists(parentNode, true);
        if (stat1 == null) {
            parentNode = zooKeeper.create(parentNode, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // 设置使用watcher监听子节点
        // 每次创建节点都需要这么写一下，为了实现监听功能
        List<String> children = zooKeeper.getChildren(parentNode, true);
        System.out.println(children.stream().reduce((c1, c2) -> c1 + ", " + c2));

        // 建立子节点， Watcher中会触发子节点更新事件
        zooKeeper.create(parentNode + "/c1", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                (int rc, String path, Object ctx, String name, Stat stat) -> {
                    System.out.println("节点创建情况: " + rc + ", " + path + ", " + ctx + ", " + name + ", " + stat);
                }, "custom context children node");
        // 注册默认watcher
        zooKeeper.getData(parentNode + "/c1", true, stat);
        // 设置数据
        zooKeeper.setData(parentNode + "/c1", "{\"name\": \"zh\"}".getBytes(), -1);
        // 第二次设置相同数据，同样会触发事件，因为版本变化了
        zooKeeper.setData(parentNode + "/c1", "{\"name\": \"zh\"}".getBytes(), -1);
        // 异步获取数据
        zooKeeper.getData(parentNode + "/c1", true,
                (int rc, String path, Object ctx, byte[] data, Stat stat)
                        -> {
                    System.out.println("异步： " + rc + ", " + path + ", " + ctx + ", " + new String(data) +  ", " + stat);
                }, "异步获取数据");
//        zooKeeper.close();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

}
