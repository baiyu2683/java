package com.zh.netty.handler;

import com.zh.model.Login;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhangheng
 * @date 2019/12/23
 */
@ChannelHandler.Sharable
public class LoginHandler extends SimpleChannelInboundHandler<Login.LoginRequestDTO> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Login.LoginRequestDTO loginRequestDTO) throws Exception {

    }
}
