package com.zh.operator;

import java.util.Random;

/**
 * Created by zh on 2017-09-28.
 */
public class BitManipulation {

    public static void main(String[] args) {
        Random rand = new Random(47);
        int i = rand.nextInt();
        int j = rand.nextInt();
        printBinaryInt("-1", -1);
        printBinaryInt("+1", +1);
        int maxpos = 2147483647;
        printBinaryInt("maxpos", maxpos);
        int maxneg = -2147483648;
        printBinaryInt("maxneg", maxneg);
        printBinaryInt("i", i);
        printBinaryInt("~i", ~i);
        printBinaryInt("-i", -i);
        printBinaryInt("j", j);
        printBinaryInt("i & j", i & j);
        printBinaryInt("i | j", i | j);
        printBinaryInt("i ^ j", i ^ j);
        printBinaryInt("i << 5", i << 5);
        printBinaryInt("i >> 5", i >> 5);
        printBinaryInt("(~i) >> 5", (~i) >> 5);
        printBinaryInt("i >>> 5", i>>>5);
        printBinaryInt("(~i) >>> 5", (~i) >>> 5);
    }

    static void printBinaryInt(String s, int i ) {
        System.out.println(s + ", int: " + i + ". binary:\n   " +
            Integer.toBinaryString(i));
    }

    static void printBinaryInt(String s, long l) {
        System.out.println(s + ", long: " + l + ". binary:\n   " +
                Long.toBinaryString(l));
    }
}
