package com.zh.flyweight;

public class MainEntry {

    public static void main(String[] args) {
        for (int i = 0 ; i < 10 ; i++) {
            FlyWeight flyWeight = FlyWeightFactory.get(String.valueOf(i % 2));
            flyWeight.operation(String.valueOf(i));
        }
        System.out.println(FlyWeightFactory.size());
    }
}
