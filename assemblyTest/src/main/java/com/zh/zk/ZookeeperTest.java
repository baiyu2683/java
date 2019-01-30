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

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws InterruptedException{
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for (int i = 0 ; i < 10 ; i++) {
            new Thread(() -> {
                ZooKeeper zooKeeper = null;
                try {
                    cyclicBarrier.await();
                    zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, new ZookeeperTest());
                    String path = zooKeeper.create("/home", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    System.out.println("成功-" + Thread.currentThread().getName());
                } catch (KeeperException | IOException | InterruptedException | BrokenBarrierException e) {
                    System.out.println("失败-" + Thread.currentThread().getName() + " " + e.getMessage());
                } finally {
                    if (zooKeeper != null) {
                        try {
                            zooKeeper.close();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(30);
    }

}
