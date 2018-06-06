package com.zh.generics;

/**
 * 由于数组协变性，1l这个long型可以在编译器赋值，但是运行会报错
 * Created by zh on 2017-06-18.
 */
public class Array26 {
    public static void main(String[] args) {
        Number[] numbers = new Integer[10];
        numbers[0] = 1l;
        System.out.println(numbers[0]);
    }
}
