package com.zh;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

public class ToIntegerDecoder2Test {

    @Test
    public void test() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new ToIntegerDecoder2());

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(1);

        ByteBuf read = byteBuf.duplicate();

        Assert.assertTrue(embeddedChannel.writeInbound(byteBuf));
        Assert.assertTrue(embeddedChannel.finish());

        ByteBuf readIn = embeddedChannel.readInbound();
        System.out.println(readIn);
    }
}
