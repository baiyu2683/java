package com.zh.polymorphic;

/**
 * Created by zh on 2017-04-04.
 */
public class Shapes {
    private static RandomShapeGenerator generator = new RandomShapeGenerator();

    public static void main(String[] args) {
        Shape[] shapes = new Shape[9];
        for(int i = 0; i < shapes.length; i++) {
            shapes[i] = generator.next();
        }
        for (Shape shape : shapes) {
            shape.draw();
            shape.show();
        }
    }
}
