package com.zh;

import com.zh.log4j.Log4j;

public class TestLog4j {

    public static void main(String[] args) {
        Log4j.init(TestLog4j.class);

        Log4j.info("asdfasdf");
    }
}
