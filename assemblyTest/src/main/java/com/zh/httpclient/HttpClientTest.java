package com.zh.httpclient;


import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.TimeUnit;

public class HttpClientTest {

    public static void main(String[] args) {
        //注册访问协议相关的Socket工厂
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
                .build();

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10)
                .setConnectTimeout(10)
                .setSocketTimeout(10)
                .setCircularRedirectsAllowed(false)
                .build();

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionTimeToLive(5, TimeUnit.MINUTES)
                .evictIdleConnections(10, TimeUnit.SECONDS)
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36")
                .setConnectionManagerShared(false)
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        for (;;) {
            try {
                HttpGet httpGet = new HttpGet("https://www.baidu.com/");
                RequestConfig config = RequestConfig.custom()
//                        .setProxy(new HttpHost("127.0.0.1", 8088))
                        .build();
                httpGet.setConfig(config);
                httpGet.setHeader("content-type", "text/html");
                HttpResponse response = httpClient.execute(httpGet);
                System.out.println(EntityUtils.toString(response.getEntity()));
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
