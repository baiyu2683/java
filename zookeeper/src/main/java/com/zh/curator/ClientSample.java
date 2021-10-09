package com.zh.curator;

import com.google.common.collect.Lists;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.AuthInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ClientSample {

    public static String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    public static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
    public static CuratorFramework client = CuratorFrameworkFactory
            .builder()
//            .authorization("digest", "zh2683:root".getBytes(StandardCharsets.UTF_8))
            .retryPolicy(retryPolicy)
            .connectString(connectString)
            .sessionTimeoutMs(6000)   // 设置超时时间，如果超时curator会自动重连
            .build();

    public static CuratorFramework client() {
        return CuratorFrameworkFactory
                .builder()
//                .authorization("auth", "zh2683:root".getBytes(StandardCharsets.UTF_8))
                .retryPolicy(retryPolicy)
                .connectString(connectString)
                .sessionTimeoutMs(6000)   // 设置超时时间，如果超时curator会自动重连
                .build();
    }

    public static CuratorFramework client(AuthInfo authInfo) {
        return CuratorFrameworkFactory
                .builder()
                .authorization(Lists.newArrayList(authInfo))
                .retryPolicy(retryPolicy)
                .connectString(connectString)
                .sessionTimeoutMs(6000)   // 设置超时时间，如果超时curator会自动重连
                .build();
    }
}
