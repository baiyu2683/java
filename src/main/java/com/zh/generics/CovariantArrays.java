package com.zh.generics;

/**
 * Created by zh on 2017-06-18.
 */
public class CovariantArrays {
    public static void main(String[] args) {
        Fruit[] fruits = new Apple[10]; //编译时类型是Fruits，运行时类型是Apple
        fruits[0] = new Apple();
        fruits[1] = new Jonathan(); //编译允许，运行时会报错
        try {
            fruits[0] = new Fruit();
        } catch (Exception e){}
        try {
            fruits[0] = new Orange();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class Fruit {
    public String show() {
        return this.getClass().getName();
    }
}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}
