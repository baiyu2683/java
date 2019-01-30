package com.zh.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 测试并发创建节点
 */
public class ZookeeperTest implements Watcher {

    private static final String local_dir = "/lock";

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws InterruptedException{
        int count = Runtime.getRuntime().availableProcessors();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        for (int i = 0 ; i < count ; i++) {
            new Thread(() -> {
                ZooKeeper zooKeeper = null;
                try {
                    cyclicBarrier.await();
                    zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, new ZookeeperTest());
                    // 获得锁
                    String path = zooKeeper.create(local_dir, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    System.out.println("成功-" + Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(5);
                    // 释放锁
                    zooKeeper.delete(local_dir, -1);
                } catch (KeeperException | IOException | InterruptedException | BrokenBarrierException e) {
                    System.out.println("失败-" + Thread.currentThread().getName() + " " + e.getMessage());
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(20);
    }

}
