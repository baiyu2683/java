package com.zh.factory.simplefactory;

public class SimpleProductFactory {

    public enum ProductEnum {
        ContreateProduct1,
        ConcreateProduct2;
    }

    public static Product produce(ProductEnum productType) {
        switch (productType) {
            case ContreateProduct1:
                return new ConcreateProduct1();
            case ConcreateProduct2:
                return new ConcreateProduct2();
            default:
                    return null;
        }
    }
}
