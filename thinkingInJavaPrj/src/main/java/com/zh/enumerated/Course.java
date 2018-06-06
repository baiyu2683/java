package com.zh.enumerated;

import com.zh.util.Enums;

/**
 * 枚举的枚举
 * Created by zh on 2017-03-19.
 */
public enum  Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class);

    private Food[] values;

    Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return Enums.random(values);
    }
}
