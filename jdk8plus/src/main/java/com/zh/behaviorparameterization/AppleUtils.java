package com.zh.behaviorparameterization;

import com.zh.entity.Apple;
import com.zh.enums.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AppleUtils {
    // 删选绿苹果，jdk8之前的写法
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        if (inventory == null || inventory.size() == 0) {
            return result;
        }
        for (Apple apple : inventory) {
            if (apple.getColor() == Color.GREEN) {
                result.add(apple);
            }
        }
        return result;
    }
    // 将颜色作为参数的一个重载方法
    // 如果有其他条件还需要增加其他的参数
    public static List<Apple> filterGreenApples(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        if (inventory == null || inventory.size() == 0) {
            return result;
        }
        for (Apple apple : inventory) {
            if (apple.getColor() == color) {
                result.add(apple);
            }
        }
        return result;
    }
    // 将颜色参数抽象为一种行为，并传递都函数参数中
    public static  List<Apple> filterGreenApples(List<Apple> inventory, AppleGreenPredicate appleGreenPredicate) {
        List<Apple> result = new ArrayList<>();
        if (inventory == null || inventory.size() == 0) {
            return result;
        }
        for (Apple apple : inventory) {
            if (appleGreenPredicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
    // 将list抽象化，脱离Apple类型限制
    public static <T> List<T> filter(List<T> inventory, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        if (inventory == null || inventory.size() == 0) {
            return result;
        }
        for (T t : inventory) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }
}
