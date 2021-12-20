package com.zh.bridge;

public class RefinedAbstractionA extends Abstraction {

    public RefinedAbstractionA(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        System.out.println("我是A, 我的impelmentor是" + implementor.operation());
    }
}
