package com.zh.polymorphic;

/**
 * Created by zh on 2017-04-04.
 */
public class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Circle.draw()");
    }
    public void erase() {
        System.out.println("Circle.erase()");
    }
}
