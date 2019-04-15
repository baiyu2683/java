package com.zh.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import sun.misc.Unsafe;

public class MyHandler extends SimpleChannelInboundHandler<FullHttpMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpMessage msg) throws Exception {
        System.out.println("消息..");
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.buffer(100).writeBytes("asdf".getBytes("utf-8")));
        fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,
                "text/plain;charset=UTF-8");
        ctx.channel().writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
        Unsafe unsafe = Unsafe.getUnsafe();
    }
}
