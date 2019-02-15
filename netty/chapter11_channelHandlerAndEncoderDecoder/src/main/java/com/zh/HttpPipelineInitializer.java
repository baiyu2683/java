package com.zh;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 增加http支持，没有处理http包分为多部分的聚合问题
 */
public class HttpPipelineInitializer extends ChannelInitializer<Channel> {

    private final boolean client;

    public HttpPipelineInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (client) {
            // 是客户端，对请求编码，对响应解码
            pipeline.addLast("decoder", new HttpRequestEncoder())
                    .addLast("encoder", new HttpResponseDecoder());
        } else {
            // 是服务端，对请求解码，对响应编码
            pipeline.addLast("decoder", new HttpRequestDecoder())
                    .addLast("encoder", new HttpResponseEncoder());
        }
    }
}
