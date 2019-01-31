package com.zh;

import com.zh.handler.TestInHandler;
import com.zh.handler.TestOutHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zh2683
 */
public class MyServer {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .localAddress(1081)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // TODO
                        ch.pipeline()
                                .addLast(new TestInHandler())
                                .addLast(new TestOutHandler());
                    }
                });
        ChannelFuture channelFuture = bootstrap.bind().sync();
        channelFuture.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("启动..");
            } else {
                future.cause().printStackTrace();
            }
        });
    }
}
