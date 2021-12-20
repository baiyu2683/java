package com.zh.bridge;

public class RefinedAbstractionB extends Abstraction {

    public RefinedAbstractionB(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        System.out.println("我是B, 我的impelmentor是" + implementor.operation());
    }
}
