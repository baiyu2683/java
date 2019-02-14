package com.zh;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

public class ToLongDecoder2Test {

    @Test
    public void test() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new ToLongDecoder2());

        ByteBuf byteBuf = Unpooled.buffer();
        // 只写一个int值，4字节，Decoder中读取的是一个long值，8字节，会报错
        byteBuf.writeInt(1);

        ByteBuf read = byteBuf.duplicate();

        Assert.assertFalse(embeddedChannel.writeInbound(byteBuf));
        Assert.assertFalse(embeddedChannel.finish());
    }
}
