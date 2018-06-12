package com.zh.state.state;

import com.zh.state.context.IContext;

import java.util.concurrent.TimeUnit;

public class OpenState implements IState {
    @Override
    public void handle(IContext context) throws InterruptedException {
        System.out.println("current : open,  next : close");
        TimeUnit.SECONDS.sleep(6);
        context.setState(IState.closeState);
    }
}
