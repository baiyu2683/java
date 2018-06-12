package com.zh.state.state;

import com.zh.state.context.IContext;

import java.util.concurrent.TimeUnit;

public class RunningState implements IState {
    @Override
    public void handle(IContext context) throws InterruptedException {
        System.out.println("current : running,  next : stop");
        TimeUnit.SECONDS.sleep(5);
        context.setState(IState.stopState);
    }
}
