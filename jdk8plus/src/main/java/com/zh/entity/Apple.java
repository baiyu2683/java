package com.zh.entity;

import com.zh.enums.Color;

public class Apple {

    private Color color;

    private float weight;

    public Apple(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
