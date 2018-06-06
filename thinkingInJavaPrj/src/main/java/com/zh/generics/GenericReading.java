package com.zh.generics;

import com.zh.polymorphic.CovariantReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by zh on 2017-06-18.
 */
public class GenericReading {
    static <T> T readExact(List<T> list) {
        return list.get(0);
    }
    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruits = Arrays.asList(new Fruit());
    static void f1() {
        Apple a = readExact(apples);
        Fruit f = readExact(fruits);
        f = readExact(apples);
    }
    static class Reader<T> {
        T readExact(List<T> list) {
            return list.get(0);
        }
    }
    static void f2() {
        Reader<Fruit> fruitReader = new Reader<>();
        //List<Apple> 需要返回 Fruit，这是不允许的
//        Fruit f = fruitReader.readExact(apples);
    }

    public static void main(String[] args) {
        Fruit f = apples.get(0); //这里是允许的
//        List<Fruit> fruits = new ArrayList<Apple>(); //报错，Apple的list不能赋值给Fruit的list
        List<? extends Fruit> fruits = new ArrayList<Apple>();
        fruits.get(0).show();
    }
}
