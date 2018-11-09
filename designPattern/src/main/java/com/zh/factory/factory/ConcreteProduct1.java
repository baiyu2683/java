package com.zh.factory.factory;

public class ConcreteProduct1 extends Product {
    @Override
    public void use() {
        System.out.println(this.getClass().getSimpleName());
    }
}
