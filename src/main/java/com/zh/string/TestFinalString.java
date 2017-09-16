package com.zh.string;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 测试字符串不可变
 * Created by zh on 2017-09-16.
 */
public class TestFinalString {

    public static void main(String[] args) {
        //1. HashSet 加入StringBuilder和String
//        HashSet<StringBuilder> stringBuilders = new HashSet<>();
//        StringBuilder sb1 = new StringBuilder("123");
//        StringBuilder sb2 = new StringBuilder("12");
//
//        stringBuilders.add(sb1);
//        stringBuilders.add(sb2);
//        System.out.println(stringBuilders.toString());
//
//        sb2.append("3");
//        System.out.println(stringBuilders.toString());
//
//        HashSet<String> strings = new HashSet<>();
//        String s1 = "123";
//        String s2 = "12";
//
//        strings.add(s1);
//        strings.add(s2);
//        System.out.println(stringBuilders.toString());
//
//        s2 = s2 + "3";
//        System.out.println(strings.toString());

        //2. 值传递时在函数中修改stringbuilder和string
        String s = "123";
        String ss = new String("123");
        StringBuilder sb = new StringBuilder("123");
        change(s);
        System.out.println(s);
        change(ss);
        System.out.println(ss);
        change(sb);
        System.out.println(sb.toString());


    }

    public static String change(String s) {
        s += "1";
        return s;
    }

    public static StringBuilder change(StringBuilder sb) {
        sb.append("1");
        return sb;
    }
}
