package com.zh;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestProp {

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        List<String> tmps = list.subList(0,2);
        System.out.println(tmps.toString());
    }

}
