package com.zh;

import com.zh.interfaces.Months;

/**
 * Created by zh on 2017-04-13.
 */
public class TestInterface {
    public static void main(String[] args) {
        //static的
        System.out.println(Months.APRIL);
        //final的，不能修改
//        Months.APRIL = 7;
    }
}
