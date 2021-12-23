package com.zh.decorator;

public class Robot extends Changer {
    public Robot(Transform transform) {
        super(transform);
    }

    @Override
    public void move() {
        super.move();
        say();
    }

    public void say() {
        System.out.println("扩展行为：robot");
    }
}
