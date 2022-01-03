package com.zh.flyweight;

/**
 * 具体对象
 */
public class ConcreateFlyWeight implements FlyWeight {

    private String internalState;

    public ConcreateFlyWeight(String internalState) {
        this.internalState = internalState;
    }

    @Override
    public void operation(String externalState) {
        System.out.println("内部状态: " + internalState);
        System.out.println("外部状态： " + externalState);
    }
}
