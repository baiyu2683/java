package com.zh.polymorphic;

/**
 * Created by zh on 2017-04-04.
 */
public class Triangle extends Shape {
    public void draw() {
        System.out.println("Triangle.draw()");
    }
    public void erase() {
        System.out.println("Triangle.erase()");
    }
}
