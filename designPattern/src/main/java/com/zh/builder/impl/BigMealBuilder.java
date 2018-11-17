package com.zh.builder.impl;

import com.zh.builder.Meal;
import com.zh.builder.MealBuilder;

public class BigMealBuilder implements MealBuilder {

    private Meal meal;

    public BigMealBuilder() {
        this.meal = new Meal();
    }

    @Override
    public MealBuilder buildFood() {
        meal.setFood("鱼翅");
        return this;
    }

    @Override
    public MealBuilder buildDrink() {
        meal.setDrink("82年的拉菲");
        return this;
    }

    @Override
    public Meal build() {
        return meal;
    }
}
