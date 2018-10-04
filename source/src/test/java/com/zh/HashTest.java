package com.zh;

import org.junit.Test;

/**
 * @Author zh2683
 */
public class HashTest {

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
