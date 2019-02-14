package com.zh;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * ReplayingDecoder继承了ByteToMessageDecoder，覆盖了callDecode()方法，会捕获数据不够时抛出的异常，下次能够继续调用
 *
 * 如何抛出异常: 这里的in是ReplayingDecoderByteBuf，在read时会检查数据是否足够，不够抛出Error(io.netty.util.Signal)
 *
 */
public class ToLongDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(in.readLong());
    }
}
