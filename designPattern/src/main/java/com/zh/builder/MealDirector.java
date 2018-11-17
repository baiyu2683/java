package com.zh.builder;

import com.zh.builder.impl.SimpleMealBuilder;

public class MealDirector {

    private MealBuilder mealBuilder = new SimpleMealBuilder();

    public void setMealBuilder(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    public Meal buildMeal() {
        return mealBuilder
                .buildFood()
                .buildDrink()
                .build();
    }
}
