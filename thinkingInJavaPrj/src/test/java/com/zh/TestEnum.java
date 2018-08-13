package com.zh;

public class TestEnum {

    enum Cases {
        Name("Name", () -> {
            System.out.println("打印名字..");
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
        System.out.println(cases.name);
        cases.consumer.run();
        System.out.println(Cases.Name.name());
        System.out.println(Cases.Name.ordinal());
    }
}
