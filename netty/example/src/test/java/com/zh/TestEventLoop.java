package com.zh;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TestEventLoop {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup work = new NioEventLoopGroup();
        NioEventLoopGroup boss = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boss, work) // boss 监听OP_ACCEPT事件， work处理其他
                .channel(NioServerSocketChannel.class)  // 初始化channel工厂
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new LoggingHandler(LogLevel.INFO));
                    }
                });
        ChannelFuture future = serverBootstrap.bind(10001).sync();
        future.addListener(future1 -> {
            if (future.isSuccess()) {
                System.out.println("成功");
            } else {
                System.out.println("失败");
            }
        });
    }
}
