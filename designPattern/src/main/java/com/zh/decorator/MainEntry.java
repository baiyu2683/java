package com.zh.decorator;

public class MainEntry {

    public static void main(String[] args) {
        Transform car = new Car();
        Transform transform = new AirPlane(car);
        transform.move();

        transform = new Robot(car);
        transform.move();
    }
}
