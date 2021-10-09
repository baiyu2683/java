package com.zh.curator.acl;

import com.google.common.collect.Lists;
import com.zh.curator.ClientSample;
import org.apache.curator.framework.AuthInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zk权限控制
 * zookeeper链接时可以指定用户名密码链接
 */
public class ACLSample {

    private static final String ACL_PATH = "/acl-sample";

    public static void main(String[] args) throws Exception {
        // 客户端权限认证
        CuratorFramework client = ClientSample.client(new AuthInfo("digest", "zh2683:root".getBytes(StandardCharsets.UTF_8)));
        client.start();

        CuratorCache cache = CuratorCache.builder(client, ACL_PATH).build();
        cache.start();
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forCreates(childData -> {
                    System.out.println(childData.getPath() + ", " + Thread.currentThread().getName());
                })
                .build();
        cache.listenable().addListener(listener);
        List<ACL> acls = new ArrayList<>();
        Stat stat = client.checkExists().forPath(ACL_PATH);
        // 为用户增加权限
        Id user1 = new Id("digest", DigestAuthenticationProvider.generateDigest("zh2683:12345"));
        Id user2 = new Id("digest", DigestAuthenticationProvider.generateDigest("zh2683:123456"));
        acls.add(new ACL(ZooDefs.Perms.ALL, user1));
        acls.add(new ACL(ZooDefs.Perms.READ, user2));
        acls.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.CREATE, user2));

        if (stat == null) {
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(acls)
                    .forPath(ACL_PATH, "name".getBytes(StandardCharsets.UTF_8));
        }

        client = ClientSample.client(new AuthInfo("digest", "zh2683:123456".getBytes(StandardCharsets.UTF_8)));
        client.start();
        String tempNode = ACL_PATH + "/temp";
        Stat tempNodeStat = client.checkExists().forPath(tempNode);
        if (tempNodeStat == null) {
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(tempNode, "name".getBytes(StandardCharsets.UTF_8));
            List<String> children = client.getChildren()
                    .forPath(tempNode);
            children.stream().forEach(System.out::println);
            List<ACL> aclList = client.getACL()
                    .forPath(ACL_PATH);
            aclList.stream().forEach(acl -> {
                System.out.println(acl.getId());
            });
        }
        new CountDownLatch(1).await();
    }
}
