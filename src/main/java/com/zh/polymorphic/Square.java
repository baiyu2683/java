package com.zh.polymorphic;

/**
 * Created by zh on 2017-04-04.
 */
public class Square extends Shape {
    public void draw() {
        System.out.println("Square.draw()");
    }
    public void erase() {
        System.out.println("Square.erase()");
    }
}
