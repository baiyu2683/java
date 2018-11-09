package com.zh.factory.simplefactory;

public class MainEntry {

    public static void main(String[] args) {
        Product product = SimpleProductFactory.produce(SimpleProductFactory.ProductEnum.ContreateProduct1);
        System.out.println(product.getClass().getName());

        product = SimpleProductFactory.produce(SimpleProductFactory.ProductEnum.ConcreateProduct2);
        System.out.println(product.getClass().getName());
    }
}
