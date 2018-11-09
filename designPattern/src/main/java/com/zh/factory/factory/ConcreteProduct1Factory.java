package com.zh.factory.factory;

public class ConcreteProduct1Factory extends Factory {

    @Override
    public Product produce() {
        return new ConcreteProduct1();
    }
}
