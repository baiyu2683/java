package com.zh.chapter1.exercise;

/**
 * 将一个正整数N转换为一个二进制表示的字符串
 * @Author zh2683
 */
public class Exercise119 {

    public static void main(String[] args) {
        System.out.println(int2Binary(10));
        System.out.println(int2Binary(11));
        System.out.println(int2Binary(101));
        System.out.println(Integer.toBinaryString(101));
    }

    private static String int2Binary(int N) {
        StringBuilder resultBinary = new StringBuilder();
        for (int n = N ; n > 0 ; n /= 2) {
            resultBinary.insert(0, n % 2);
        }
        return resultBinary.toString();
    }
}
