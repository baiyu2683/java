package com.zh.state.context;

import com.zh.state.state.IState;

/**
 * 上下文实现
 */
public class Context implements IContext {

    private IState state;

    @Override
    public void setState(IState state) {
        this.state = state;
    }

    @Override
    public void request(String param) throws Exception{
        System.out.println("param : " + param);
        state.handle(this);
    }

}
