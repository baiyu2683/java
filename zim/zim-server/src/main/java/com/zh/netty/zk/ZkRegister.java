package com.zh.netty.zk;

import com.zh.netty.constants.ServerConstants;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * zookeeper节点注册
 * serverIp由启动参数传入
 */
@Log4j2
@Component
public class ZkRegister implements Runnable {

    @Value("${im.port}")
    private int port;

    @Value("${im.registryAddress}")
    private String registryAddress;

    public void register() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        String serverIp = System.getProperty(ServerConstants.SERVER_IP_KEY);
        if (StringUtils.isBlank(serverIp)) {
            serverIp = ServerConstants.DEFAULT_SERVER_IP;
        }
        log.info("server_ip:" + serverIp);
        String serverNode = String.format(ServerConstants.SERVER_NODE_TEMPLATE,
                ServerConstants.ZK_SERVER_ROOT_NODE,
                serverIp,
                port);
        try {
            // 节点注册，断线后3秒，节点清除
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);
            CuratorFramework client = CuratorFrameworkFactory
                    .builder()
                    .connectString(registryAddress)
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
