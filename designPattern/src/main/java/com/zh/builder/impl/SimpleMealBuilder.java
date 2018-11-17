package com.zh.builder.impl;

import com.zh.builder.Meal;
import com.zh.builder.MealBuilder;

public class SimpleMealBuilder implements MealBuilder {

    private Meal meal;

    public SimpleMealBuilder() {
        this.meal = new Meal();
    }

    @Override
    public MealBuilder buildFood() {
        meal.setFood("米饭");
        return this;
    }

    @Override
    public MealBuilder buildDrink() {
        meal.setDrink("三无饮料");
        return this;
    }

    @Override
    public Meal build() {
        return meal;
    }
}
