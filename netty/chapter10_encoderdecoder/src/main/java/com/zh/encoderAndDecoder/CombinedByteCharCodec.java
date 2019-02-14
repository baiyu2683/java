package com.zh.encoderAndDecoder;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * 整合编码器和解码器形成一个具有两种功能的编解码器
 * 但是又不会令编码器和解码器失去重用性
 * @author zh2683
 */
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {

    public CombinedByteCharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
