package com.zh.state.state;

import com.zh.state.context.IContext;

/**
 * 状态
 */
public interface IState {

    IState closeState = new CloseState();
    IState openState = new OpenState();
    IState runningState = new RunningState();
    IState stopState = new StopState();

    void handle(IContext context) throws InterruptedException;
}
