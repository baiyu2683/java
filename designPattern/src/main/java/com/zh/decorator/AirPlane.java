package com.zh.decorator;

public class AirPlane extends Changer {
    public AirPlane(Transform transform) {
        super(transform);
    }

    @Override
    public void move() {
        super.move();
        fly();
    }

    public void fly() {
        System.out.println("扩展行为：fly");
    }
}
