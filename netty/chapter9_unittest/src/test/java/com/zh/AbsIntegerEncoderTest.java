package com.zh;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

public class AbsIntegerEncoderTest {

    @Test
    public void testEncoded() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 1 ; i < 10 ; i++) {
            buf.writeInt(i * -1);
        }

        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());

        Assert.assertTrue(channel.writeOutbound(buf));
        // 标记完成，不能继续写数据，只能读
        Assert.assertTrue(channel.finish());

        // read
        for (int i = 1 ; i < 10 ; i++) {
            Assert.assertEquals(i, (int)channel.readOutbound());
        }

        Assert.assertNull(channel.readOutbound());
    }

}
