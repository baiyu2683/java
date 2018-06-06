package com.zh.interfaces;

/**
 * Created by zh on 2017-04-09.
 */
public class TestPrintSub extends TestPrint {
    private int count = 10;
    @Override
    public void show() {
        System.out.println(count);
    }

    public static void main(String[] args) {
        TestPrint testPrint = new TestPrintSub();
    }
}
