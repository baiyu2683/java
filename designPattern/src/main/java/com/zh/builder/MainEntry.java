package com.zh.builder;

import com.zh.builder.impl.BigMealBuilder;
import com.zh.builder.impl.SimpleMealBuilder;

/**
 * 建造者模式
 */
public class MainEntry {

    public static void main(String[] args) {
        MealDirector director = new MealDirector();

        director.setMealBuilder(new SimpleMealBuilder());
        Meal meal = director.buildMeal();
        System.out.println(meal.getFood() + "-" + meal.getDrink());

        director.setMealBuilder(new BigMealBuilder());
        meal = director.buildMeal();
        System.out.println(meal.getFood() + "-" + meal.getDrink());
    }
}
