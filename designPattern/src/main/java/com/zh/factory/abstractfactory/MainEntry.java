package com.zh.factory.abstractfactory;

import com.zh.factory.abstractfactory.factory.AbstractFactory;
import com.zh.factory.abstractfactory.factory.ConcreatFactory2;
import com.zh.factory.abstractfactory.factory.ConcreteFactory1;
import com.zh.factory.abstractfactory.product.AbstractProductA;
import com.zh.factory.abstractfactory.product.AbstractProductB;

/**
 * 抽象工厂模式，
 * 对于增加AbstractProduct，即产品类别，需要修改每个子工厂，不符合开闭原则
 * 但是对于增加AbstractProduct的子类，即具体产品，只需要增加一个新的工厂类，符合开闭原则
 */
public class MainEntry {

    public static void main(String[] args) {
        AbstractFactory abstractFactory = new ConcreteFactory1();
        AbstractProductA a = abstractFactory.productA();
        a.use();
        AbstractProductB b = abstractFactory.productB();
        b.use();

        abstractFactory = new ConcreatFactory2();
        a = abstractFactory.productA();
        a.use();
        b = abstractFactory.productB();
        b.use();
    }
}
