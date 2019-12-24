package com.zh.netty.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

/**
 * zookeeper节点注册
 */
@Slf4j
@Component
public class ZkRegister implements Runnable {

    public void register() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        String serverNode = "/zim-server/127.0.0.1:10099";
        try {
            // 节点注册，断线后3秒，节点清除
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);
            CuratorFramework client = CuratorFrameworkFactory
                    .builder()
                    .connectString("127.0.0.1:2181")
                    .sessionTimeoutMs(3 * 1000)
                    .retryPolicy(retryPolicy)
                    .build();
            client.start();
            // 节点不存在注册，存在忽略
            Stat serverStat = client.checkExists().forPath(serverNode);
            if (serverStat == null) {
                // 临时节点，掉线zk会自动删除
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath(serverNode);
            }
            // 断线后没有在sessionTimeOut内重新连接zk时，会收到异常信息
            client.getConnectionStateListenable().addListener((curatorFramework, newState) -> {
                if(newState == ConnectionState.LOST){
                    while(true){
                        try {
                            if(curatorFramework.getZookeeperClient().blockUntilConnectedOrTimedOut()){
                                curatorFramework.create()
                                        .creatingParentsIfNeeded()
                                        .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                                        .forPath(serverNode);
                                break;
                            }
                        } catch (InterruptedException e) {
                            break;
                        } catch (Exception e){
                        }
                    }
                }
            });
            log.info("服务注册成功: ");
        } catch (Exception e) {
            log.error("服务注册失败: ", e);
        }
    }

}
