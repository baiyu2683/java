package com.zh;

import java.util.function.Consumer;
import java.util.function.Function;

public class TestEnum {

    enum Cases {
        Name("asdf", () -> {
            System.out.println(1);
        });

        Cases(String name, Runnable consumer) {
            this.name = name;
            this.consumer = consumer;
        }

        private String name;
        private Runnable consumer;
    }

    public static void main(String[] args) {
        Cases cases = Cases.valueOf("Name");
        System.out.println(Cases.Name.name());
        System.out.println(Cases.Name.ordinal());
    }
}
