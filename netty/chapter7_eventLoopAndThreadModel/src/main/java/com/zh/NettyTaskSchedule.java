package com.zh;

import io.netty.channel.embedded.EmbeddedChannel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * TODO 没有效果？？？
 * eventloop只绑定一个thread，不会有动态创建线程的过程，也比jdk的少了很多切换上下文的时间
 */
public class NettyTaskSchedule {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.eventLoop().schedule(() -> {
            System.out.println("123");
        }, 10, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(20);
    }
}
