package com.zh;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.util.CharsetUtil;

/**
 * 自定义分隔符帧处理
 */
public class DelimiterHandlerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        ByteBuf delimiter = Unpooled.buffer();
        delimiter.writeCharSequence("@@", CharsetUtil.UTF_8);

        pipeline.addLast(new DelimiterBasedFrameDecoder(64 * 1024, delimiter));
        pipeline.addLast(new FrameHandler());
    }

    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            // msg 为单个按行获取的帧
        }
    }
}
