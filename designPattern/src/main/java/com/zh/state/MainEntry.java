package com.zh.state;

import com.zh.state.context.Context;
import com.zh.state.context.IContext;
import com.zh.state.state.IState;

/**
 * 测试
 * 状态
 *   开 ---> 关 ---> 运行 ---> 停止
 *   \                            \
 *   \- - - - - - <- -  - - - - - \
 */
public class MainEntry {
    public static void main(String[] args) {
        IContext context = new Context();
        context.setState(IState.openState);
        for (int i = 0 ; i < 8 ; i++) {
            try {
                context.request(String.valueOf(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
