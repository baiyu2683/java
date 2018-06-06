package com.zh.enumerated;

import static com.zh.enumerated.Food.*;

/**
 * Created by zh on 2017-03-18.
 */
public class TypeOfFood {
    public static void main(String[] args) {
        Food food = Appetizer.SALAD;
        food = MainCourse.LASAGNE;
        food = Dessert.CAPPUCCINO;
    }
}
