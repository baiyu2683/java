package com.zh.enumerated;

/**
 * 通过在接口中实现枚举，从而将Food分组
 * Created by zh on 2017-03-18.
 */
public interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS;
    }
    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAT,
        LENTILS, HUMMOUS, VINDALOO;
    }
    enum Dessert implements Food {
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA;
    }
}
