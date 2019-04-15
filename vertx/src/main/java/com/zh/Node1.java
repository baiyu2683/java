package com.zh;


import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.TcpIpConfig;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.ConfigUtil;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Node1 {

    private static EventBus eventBus;
    private static Vertx vertx;

    public static void main(String[] args) throws InterruptedException {
        Config hazelcastConfig = ConfigUtil.loadConfig();
        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setPort(8090);
        networkConfig.setPortAutoIncrement(false);

        JoinConfig joinConfig = new JoinConfig();
        joinConfig.getMulticastConfig().setEnabled(false);

        TcpIpConfig tcpIpConfig = new TcpIpConfig();
        tcpIpConfig.setEnabled(true);
        tcpIpConfig.setConnectionTimeoutSeconds(5000);
        tcpIpConfig.addMember("localhost:8090");
        tcpIpConfig.addMember("localhost:8091");

        joinConfig.setTcpIpConfig(tcpIpConfig);

        networkConfig.setJoin(joinConfig);
        hazelcastConfig.setNetworkConfig(networkConfig);

        ClusterManager mgr = new HazelcastClusterManager(hazelcastConfig);

        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        options.setClusterPort(9090);
        options.setClustered(true);
        options.setWorkerPoolSize(20);

        CountDownLatch latch = new CountDownLatch(1);
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                vertx = res.result();
                eventBus = vertx.eventBus();
                latch.countDown();
            } else {
                // failed!
            }
        });
        latch.await();
        vertx.setPeriodic(5000, id -> {
            System.out.println(mgr.getNodes());
            System.out.println(mgr.getNodeID());
           eventBus.publish("first", "消息: " + System.currentTimeMillis());
        });
    }
}
