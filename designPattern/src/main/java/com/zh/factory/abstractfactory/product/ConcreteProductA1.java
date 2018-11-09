package com.zh.factory.abstractfactory.product;

public class ConcreteProductA1 extends AbstractProductA {
    @Override
    public void use() {
        System.out.println(this.getClass().getSimpleName());
    }
}
