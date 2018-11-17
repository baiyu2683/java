package com.zh.builder;

/**
 * 抽象meal建造者
 */
public interface MealBuilder {

    MealBuilder buildFood();

    MealBuilder buildDrink();

    Meal build();
}
