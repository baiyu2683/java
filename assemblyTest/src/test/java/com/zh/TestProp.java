package com.zh;

import java.io.*;

public class TestProp {

    public static void main(String[] args) throws IOException {
        String template = "asdf%sasdf";
        System.out.println(String.format(template, null));
    }

}
