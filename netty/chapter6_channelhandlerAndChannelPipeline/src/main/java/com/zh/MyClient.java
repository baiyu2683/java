package com.zh;

import com.zh.handler.TestInHandler;
import com.zh.handler.TestOutHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zh2683
 */
public class MyClient {

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .remoteAddress("127.0.0.1", 1081)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new TestInHandler())
                                .addLast(new TestOutHandler());
                    }
                });
        bootstrap.connect()
                .addListener(future -> {
                   if (future.isSuccess()) {
                       ChannelFuture channelFuture = (ChannelFuture)future;
                       ByteBuf byteBuf = Unpooled.buffer();
                       byteBuf.writeBytes("asdfasdf".getBytes());
                       channelFuture.channel().writeAndFlush(byteBuf);
                   }
                });
    }
}
