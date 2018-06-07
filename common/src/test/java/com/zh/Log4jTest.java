package com.zh;

import com.zh.log4j.Log4j;

public class Log4jTest {

    public static void main(String[] args) {
        show();
    }

    private static void show() {
        Log4j.info("test", "test1", "test2");
    }
}
