package com.zh.edition8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestStream {

    @Test
    public void listTest() {
        List<String> list = new ArrayList<>();
        for (int i = 0 ; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        list.parallelStream().forEach(i -> {
            System.out.println(i);
        });
    }
}
