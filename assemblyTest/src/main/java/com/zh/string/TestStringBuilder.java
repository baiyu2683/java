package com.zh.string;

public class TestStringBuilder {

    public static void main(String[] args) {
        StringBuilder tem = new StringBuilder();
        for (int index = 0; index < 10; index++) {
            tem.append("11");
        }
        System.out.println(tem);
    }
}
