package com.zh.factory.abstractfactory.factory;

import com.zh.factory.abstractfactory.product.AbstractProductA;
import com.zh.factory.abstractfactory.product.AbstractProductB;

/**
 * 抽象工厂类
 */
public abstract class AbstractFactory {

    public abstract AbstractProductA productA();

    public abstract AbstractProductB productB();
}
