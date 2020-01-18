package com.zh;

import org.junit.Test;

import com.zh.echo.EchoClientHandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.CharsetUtil;

public class EchoHandlerTest {

	
	@Test
	public void clientHandle() {
		EmbeddedChannel channel = new EmbeddedChannel(new EchoClientHandler());
		
		ByteBuf buffer = Unpooled.buffer();
		buffer.writeCharSequence("client", CharsetUtil.UTF_8);
		
		System.out.println(channel.writeOutbound(buffer));
		ByteBuf outBound = channel.readOutbound();
		System.out.println(outBound.getCharSequence(0, outBound.readableBytes(), CharsetUtil.UTF_8));
		System.out.println(channel.writeInbound(buffer));
		System.out.println(channel.finish());
	}
}
