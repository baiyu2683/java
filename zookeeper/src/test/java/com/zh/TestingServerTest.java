package com.zh;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.junit.Test;

import java.io.File;

public class TestingServerTest {

    @Test
    public void test1() throws Exception {
        TestingServer testingServer = new TestingServer(9090, new File("d:/zk-data"));
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(testingServer.getConnectString())
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .sessionTimeoutMs(6000)
                .build();
        client.start();
        System.out.println(client.getChildren().forPath("/zookeeper"));
        testingServer.close();
    }
}
