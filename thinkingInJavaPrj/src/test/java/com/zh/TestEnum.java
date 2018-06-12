package com.zh;

public class TestEnum {

    enum Cases {
        Name;
    }

    public static void main(String[] args) {
        Cases cases = Cases.valueOf("Name");
        System.out.println(Cases.Name.name());
        System.out.println(Cases.Name.ordinal());
    }
}
