package com.zh.bridge;

/**
 * abstraction和implementor通过桥接模式组合在一起
 */
public abstract class Abstraction {
    
    protected Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();
}
