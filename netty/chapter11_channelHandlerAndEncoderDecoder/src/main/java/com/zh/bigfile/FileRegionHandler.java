package com.zh.bigfile;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.FileInputStream;

/**
 * 使用FileRegion传输文件内容,不进行处理
 * 零拷贝，不会将文件复制到用户内存中
 */
public class FileRegionHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        File file = new File("e:/test.txt");
        FileInputStream in = new FileInputStream(file);
        FileRegion region = new DefaultFileRegion(
                in.getChannel(), 0, file.length()
        );
        ctx.channel().writeAndFlush(region)
                .addListener(future -> {
                    if (!future.isSuccess()) {
                        Throwable cause = future.cause();
                    }
                });
    }
}
