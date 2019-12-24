package com.zh.netty.handler;

import com.zh.model.DataWrapper;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangheng
 * @date 2019/12/24
 */
@ChannelHandler.Sharable
@Component
public class SecondDecoderHandler extends MessageToMessageDecoder<DataWrapper> {

    @Override
    protected void decode(ChannelHandlerContext ctx, DataWrapper msg, List<Object> out) throws Exception {
        switch (msg.getType()) {
            case register_request:
                out.add(msg.getRegisterRequestDTO());
                break;
            case register_response:
                out.add(msg.getRegisterResponseDTO());
                break;
            case login_request:
                out.add(msg.getLoginRequestDTO());
                break;
            case login_response:
                out.add(msg.getLoginResponseDTO());
                break;
            case UNRECOGNIZED:
                throw new IllegalArgumentException("非法信息");
            default:
                break;
        }
    }
}
