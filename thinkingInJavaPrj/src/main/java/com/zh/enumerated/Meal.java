package com.zh.enumerated;

/**
 * Created by zh on 2017-03-19.
 */
public class Meal {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for(Course course : Course.values()){
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("---------------");
        }
    }
}
