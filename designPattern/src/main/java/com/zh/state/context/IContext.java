package com.zh.state.context;

import com.zh.state.state.IState;

/**
 * 上下文
 */
public interface IContext {

    void setState(IState state);

    void request(String param) throws Exception;
}
