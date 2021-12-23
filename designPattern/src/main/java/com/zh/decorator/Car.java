package com.zh.decorator;

public class Car implements Transform {
    @Override
    public void move() {
        System.out.println("默认行为：move");
    }
}
