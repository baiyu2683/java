package com.zh.adapter;

public class Adaptee {

    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void display() {
        System.out.println("类名:" + this.getClass().getName());
    }
}
