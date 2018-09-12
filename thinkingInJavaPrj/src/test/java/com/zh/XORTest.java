package com.zh;

public class XORTest {

    public static void main(String[] args) {
        String s = "asdfasdfasdf";
        System.out.println(Integer.toBinaryString(s.hashCode()));
        int h = s.hashCode();
        System.out.println(Integer.toBinaryString(h >>> 16));
        System.out.println(Integer.toBinaryString(h ^ h>>>16));
    }
}
