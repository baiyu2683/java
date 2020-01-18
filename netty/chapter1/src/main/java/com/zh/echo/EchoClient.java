package com.zh.echo;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

	private static final String host = "127.0.0.1";
	private static final int port = 10010;
	
	public static void main(String[] args) throws Exception {
		new EchoClient().start();
	}
	
	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
			.remoteAddress(new InetSocketAddress(host, port))
			.handler(new ChannelInitializer<SocketChannel>() {
	
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline()
					.addLast(new EchoClientHandler());
				}
			});
			ChannelFuture f = b.connect().sync();
//			TimeUnit.SECONDS.sleep(3);
			f.channel().close().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
}
