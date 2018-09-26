package com.zh;

import org.junit.Test;

public class BitTest {

    @Test
    public void hash() {
        String s = "2";
        int h = s.hashCode();
        System.out.println(Integer.toBinaryString(h));
        System.out.println(Integer.toBinaryString(h >>> 16));
        h = h ^ h >>> 16;
        System.out.println(Integer.toBinaryString(h));
    }

    @Test
    public void rightMoveUnsigned() {

    }
}
