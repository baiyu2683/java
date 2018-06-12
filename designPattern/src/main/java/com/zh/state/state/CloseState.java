package com.zh.state.state;

import com.zh.state.context.IContext;

import java.util.concurrent.TimeUnit;

public class CloseState implements IState {
    @Override
    public void handle(IContext context) throws InterruptedException {
        System.out.println("current : close,  next : running");
        TimeUnit.SECONDS.sleep(1);
        context.setState(IState.runningState);
    }
}
