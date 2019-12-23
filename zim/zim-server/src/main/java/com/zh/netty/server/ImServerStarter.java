package com.zh.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zhangheng
 * @date 2019/12/23
 */
@Log4j2
@Component
public class ImServerStarter implements InitializingBean, DisposableBean {

    private NioEventLoopGroup boss = new NioEventLoopGroup();
    private NioEventLoopGroup worker = new NioEventLoopGroup();
    private ServerBootstrap serverBootstrap = new ServerBootstrap();

    @Value("${im.port}")
    private int port;

    @Override
    public void afterPropertiesSet() throws Exception {
        serverBootstrap.group(boss, worker)
                .localAddress(port)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // TODO 注册handler
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind().sync();
        channelFuture.addListener(future -> {
            if (future.isSuccess()) {
                log.info("im服务启动成功.");
                // TODO 成功之后注册到zookeeper

            } else {
                future.cause().printStackTrace();
            }
        });
    }

    @Override
    public void destroy() throws Exception {
        boss.shutdownGracefully().sync();
        worker.shutdownGracefully().sync();
        log.info("im服务关闭成功.");
    }

}
