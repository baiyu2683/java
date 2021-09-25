package com.zh;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingServer;
import org.apache.curator.test.TestingZooKeeperServer;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TestingClusterTest {

    @Test
    public void test1() throws Exception {
        TestingCluster testingCluster = new TestingCluster(3);
        testingCluster.start();
        TimeUnit.SECONDS.sleep(5);

        TestingZooKeeperServer leader = null;
        for (TestingZooKeeperServer zs : testingCluster.getServers()) {
            System.out.println(zs.getInstanceSpec().getServerId() + "-");
            System.out.println(zs.getQuorumPeer().getServerState() + "-");
            System.out.println(zs.getInstanceSpec().getDataDirectory().getAbsolutePath());
            if (zs.getQuorumPeer().getServerState().equals("leading")) {
                leader = zs;
            }
        }
        leader.kill();
        System.out.println("after leader kill:");
        TimeUnit.SECONDS.sleep(5);
        for (TestingZooKeeperServer zs : testingCluster.getServers()) {
            System.out.println(zs.getInstanceSpec().getServerId() + "-");
            System.out.println(zs.getQuorumPeer().getServerState() + "-");
            System.out.println(zs.getInstanceSpec().getDataDirectory().getAbsolutePath());
        }

        testingCluster.close();
    }
}
