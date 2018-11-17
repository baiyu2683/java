package com.zh.factory.factory;

public class ConcreteProduct2Factory extends Factory {
    @Override
    public Product produce() {
        return new ConcreteProduct2();
    }
}
