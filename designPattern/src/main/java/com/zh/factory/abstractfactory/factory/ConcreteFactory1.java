package com.zh.factory.abstractfactory.factory;

import com.zh.factory.abstractfactory.product.AbstractProductA;
import com.zh.factory.abstractfactory.product.AbstractProductB;
import com.zh.factory.abstractfactory.product.ConcreteProductA1;
import com.zh.factory.abstractfactory.product.ConcreteProductB1;

public class ConcreteFactory1 extends AbstractFactory {
    @Override
    public AbstractProductA productA() {
        return new ConcreteProductA1();
    }

    @Override
    public AbstractProductB productB() {
        return new ConcreteProductB1();
    }
}
