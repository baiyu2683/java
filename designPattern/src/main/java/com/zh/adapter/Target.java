package com.zh.adapter;

public class Target {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("类名:" + this.getClass().getName());
    }
}
