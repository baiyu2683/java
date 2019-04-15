package com.zh.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

public class HttpServer {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        SelfSignedCertificate selfSignedCertificate = new SelfSignedCertificate();
                        SslContext sslContext = SslContextBuilder
                                .forServer(selfSignedCertificate.privateKey(), selfSignedCertificate.certificate())
                                .build();

                        ch.pipeline()
                                .addLast(new SslHandler(sslContext.newEngine(ch.alloc())))
                                .addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(Integer.MAX_VALUE))
                                .addLast(new MyHandler());

                    }
                });
        try {
            ChannelFuture future = bootstrap.localAddress(8099)
                    .bind()
                    .sync();
            future.addListener(future1 -> {
                if (future1.isSuccess()) {
                    System.out.println("启动完成..");
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
