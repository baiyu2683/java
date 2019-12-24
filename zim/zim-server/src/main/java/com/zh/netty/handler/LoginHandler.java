package com.zh.netty.handler;

import com.zh.model.LoginRequestDTO;
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
public class LoginHandler extends SimpleChannelInboundHandler<LoginRequestDTO> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestDTO loginRequestDTO) throws Exception {
        System.out.println("收到..." + loginRequestDTO.toString());
    }
}
