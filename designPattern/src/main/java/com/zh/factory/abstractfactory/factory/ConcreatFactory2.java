package com.zh.factory.abstractfactory.factory;

import com.zh.factory.abstractfactory.product.AbstractProductA;
import com.zh.factory.abstractfactory.product.AbstractProductB;
import com.zh.factory.abstractfactory.product.ConcreteProductA2;
import com.zh.factory.abstractfactory.product.ConcreteProductB2;

public class ConcreatFactory2 extends AbstractFactory {
    @Override
    public AbstractProductA productA() {
        return new ConcreteProductA2();
    }

    @Override
    public AbstractProductB productB() {
        return new ConcreteProductB2();
    }
}
