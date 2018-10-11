package com.zh;

import org.junit.Test;

/**
 * @Author zh2683
 */
public class HashTest {


    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println("n     : " + Integer.toBinaryString(n));
        System.out.println("n>>>1 : " + Integer.toBinaryString(n >>> 1));
        n |= n >>> 1; // 最高位的1右移一位，之后或操作，则结果最高位有两个1
        System.out.println("n     : " + Integer.toBinaryString(n));
        System.out.println("n>>>2 : " + Integer.toBinaryString(n >>> 2));
        n |= n >>> 2; // 上面结果最高位有两个1，右移两位，这两个1右移两位，或操作，则结果最高位有4个1
        System.out.println("n     : " + Integer.toBinaryString(n));
        System.out.println("n>>>4 : " + Integer.toBinaryString(n >>> 4));
        n |= n >>> 4; // 一次类推
        System.out.println("n     : " + Integer.toBinaryString(n));
        System.out.println("n>>>8 : " + Integer.toBinaryString(n >>> 8));
        n |= n >>> 8;
        System.out.println("n     : " + Integer.toBinaryString(n));
        System.out.println("n>>>16: " + Integer.toBinaryString(n >>> 16));
        n |= n >>> 16;
        System.out.println(n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Test
    public void testTableSize() {
        System.out.println(tableSizeFor(2));
    }

    @Test
    public void hashTest() {
        int n = 64;
        int hash = 3853;
        System.out.println(hash % n);
        System.out.println(hash & (n - 1));
        System.out.println("hash : " + Integer.toBinaryString(hash));
        System.out.println("n    : " + "00000" + Integer.toBinaryString(n));
        System.out.println("n - 1: " + "000000" + Integer.toBinaryString(n - 1));
    }
}
