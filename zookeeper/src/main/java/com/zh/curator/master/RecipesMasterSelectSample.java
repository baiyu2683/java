package com.zh.curator.master;

import com.zh.curator.ClientSample;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.checkerframework.checker.units.qual.A;

import java.io.EOFException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Master选举问题（分布式锁）
 */
public class RecipesMasterSelectSample {

    static String master_path = "/curator_recipes_master_path";

    static class LeaderSelectTask implements Runnable {

        private CuratorFramework client;

        public LeaderSelectTask(CuratorFramework client) {
            this.client = client;
        }

        @Override
        public void run() {
            LeaderSelector leaderSelector = new LeaderSelector(client,
                    master_path,
                    new LeaderSelectorListener() {
                        @Override
                        public void takeLeadership(CuratorFramework client) throws Exception {
                            String threadName = Thread.currentThread().getName();
                            System.out.println("leader: " + threadName);
                            TimeUnit.SECONDS.sleep(5);
                            System.out.println("释放" + threadName);
                        }

                        @Override
                        public void stateChanged(CuratorFramework client, ConnectionState newState) {

                        }
                    });
            // 一轮选举结束后重新加入
            leaderSelector.autoRequeue();
            leaderSelector.start();
        }
    }

    static class LeaderLatchTask implements Runnable {

        private CuratorFramework client;
        private Integer id;

        public LeaderLatchTask(CuratorFramework client, Integer id) {
            this.client = client;
            this.id = id;
        }

        @Override
        public void run() {
            LeaderLatch leaderLatch = new LeaderLatch(client, master_path, String.valueOf(id));
            leaderLatch.addListener(new LeaderLatchListener() {
                @Override
                public void isLeader() {
                    System.out.println("leader: " + id);
                }
                @Override
                public void notLeader() {
                    System.out.println("not leader: " + id);
                }
            });
//            for (;;) {
                try {
                    // 仅能进行一次start
                    leaderLatch.start();
                    leaderLatch.await();
                    System.out.println("执行业务逻辑...");
                    TimeUnit.SECONDS.sleep(2);
                    leaderLatch.close();
                } catch (InterruptedException | EOFException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CuratorFramework client = ClientSample.client;
        client.start();
        Executor executor = Executors.newFixedThreadPool(10);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0 ; i < 10 ; i++) {
//            executor.execute(new LeaderSelectTask(client));
            executor.execute(new LeaderLatchTask(client, atomicInteger.incrementAndGet()));
        }
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

}
