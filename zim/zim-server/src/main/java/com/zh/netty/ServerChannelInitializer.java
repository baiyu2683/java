package com.zh.netty;

import com.zh.netty.handler.IdleHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhangheng
 * @date 2019/12/23
 */
@Component
public class ServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    @Value("${im.readerIdleTime}")
    private int readerIdleTime;

    @Value("${im.writerIdleTime}")
    private int writerIdleTime;

    @Value("${im.allIdleTime}")
    private int allIdleTime;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new IdleHandler(readerIdleTime, writerIdleTime, allIdleTime))
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufEncoder());
    }
}
