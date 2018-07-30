package com.zh.chapter1.chapter11.exercise;

/**
 * 辗转相除法计算最大公约数，并打印每次递归参数
 * p和q，如果q==0， 返回p
 * 否则计算q和p%q的最大公约数
 * @Author zh2683
 */
public class Exercise1124 {

    public static void main(String[] args) {
        System.out.println(maxCommonDivisor(1111111, 1234567));
    }

    private static int maxCommonDivisor(int p, int q) {
        System.out.println(p + ", " + q);
        if (q == 0) return p;
        else {
            int remain = p % q;
            return maxCommonDivisor(q, remain);
        }
    }
}
