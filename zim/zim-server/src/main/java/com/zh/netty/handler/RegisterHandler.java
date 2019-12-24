package com.zh.netty.handler;

import com.zh.model.RegisterRequestDTO;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

/**
 * @author zhangheng
 * @date 2019/12/23
 */
@ChannelHandler.Sharable
@Component
public class RegisterHandler extends SimpleChannelInboundHandler<RegisterRequestDTO> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RegisterRequestDTO registerDTO) throws Exception {

    }

}
