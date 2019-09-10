package com.zh.itext;


import java.util.ArrayList;
import java.util.List;

public class HtmlToWord {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++) {
            list.add((int)(Math.random() * 100));
        }
        list.stream().forEach(data -> {
            System.out.println(data);
        });
    }
}
