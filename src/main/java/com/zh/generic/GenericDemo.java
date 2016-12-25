package com.zh.generic;

/**
 * Created by zh on 2016-12-25.
 */
public class GenericDemo {
    public static void main(String[] args) {
        Shape[] shapes = new Rectangle[10];
        //运行时报错java.lang.ArrayStoreException: com.zh.generic.Circle
//        shapes[0] = new Circle();
    }
}

class Rectangle implements Shape {

}
class Circle implements Shape {

}
