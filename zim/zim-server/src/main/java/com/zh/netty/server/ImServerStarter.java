package com.zh.netty.server;

import com.zh.model.DataWrapper;
import com.zh.netty.handler.LoginHandler;
import com.zh.netty.handler.RegisterHandler;
import com.zh.netty.handler.DataWrapperDecoderHandler;
import com.zh.netty.zk.ServerRegister;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${im.readerIdleTime}")
    private int readerIdleTime;

    @Value("${im.writerIdleTime}")
    private int writerIdleTime;

    @Value("${im.allIdleTime}")
    private int allIdleTime;

    @Autowired
    private RegisterHandler registerHandler;

    @Autowired
    private LoginHandler loginHandler;

    @Autowired
    private DataWrapperDecoderHandler secondDecoderHandler;

    @Autowired
    private ServerRegister zkRegister;

    @Override
    public void afterPropertiesSet() throws Exception {
        serverBootstrap.group(boss, worker)
                .localAddress(port)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new IdleStateHandler(readerIdleTime, writerIdleTime, allIdleTime))
                                .addLast(new ProtobufVarint32FrameDecoder())
                                .addLast(new ProtobufDecoder(DataWrapper.getDefaultInstance()))
                                .addLast(secondDecoderHandler)
                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                .addLast(new ProtobufEncoder())
                                .addLast(registerHandler)
                                .addLast(loginHandler);
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind().sync();
        channelFuture.addListener(future -> {
            if (future.isSuccess()) {
                log.info("im服务启动成功.");
                zkRegister.register();
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
