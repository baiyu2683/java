package com.zh;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

public class ServerBootStrapTest {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_TIMEOUT, 5000)
                .localAddress(1090)
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("Received data");
                        byte[] content = new byte[msg.readableBytes()];
                        msg.readBytes(content);
                        System.out.println(new String(content, "utf-8"));
                    }
                });
        ChannelFuture future = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 1990));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("Server bound");
                } else {
                    System.out.println("Bound attempt failed");
                    future.cause().printStackTrace();
                }
            }
        });
        boss.shutdownGracefully().addListener(future1 -> {
            if (future1.isSuccess()) {
                System.out.println("成功");
            } else {
                System.out.println("失败");
            }
        });
        worker.shutdownGracefully().addListener(future1 -> {
            if (future1.isSuccess()) {
                System.out.println("成功");
            }  else {
                System.out.println("失败");
            }
        });
    }
}
