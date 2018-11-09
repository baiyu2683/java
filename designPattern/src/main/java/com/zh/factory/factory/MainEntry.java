package com.zh.factory.factory;

/**
 * 工厂方法模式
 * 避免了简单工厂需要在同一个工厂中生产所有产品，增加产品需要修改不符合开闭原则的问题
 */
public class MainEntry {

    public static void main(String[] args) {
        Factory factory = new ConcreteProduct1Factory();
        Product product = factory.produce();
        product.use();

        factory = new ConcreteProduct2Factory();
        product = factory.produce();
        product.use();
    }
}
