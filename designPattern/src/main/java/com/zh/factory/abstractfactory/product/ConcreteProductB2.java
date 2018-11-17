package com.zh.factory.abstractfactory.product;

public class ConcreteProductB2 extends AbstractProductB {
    @Override
    public void use() {
        System.out.println(this.getClass().getSimpleName());
    }
}
