package com.zh.netty.handler;

import com.zh.model.Register;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhangheng
 * @date 2019/12/23
 */
@ChannelHandler.Sharable
public class RegisterHandler extends SimpleChannelInboundHandler<Register.RegisterDTO> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Register.RegisterDTO registerDTO) throws Exception {

    }

}
