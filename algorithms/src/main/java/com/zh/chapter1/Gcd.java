package com.zh.chapter1;

/**
 * 求两个数字q和p的最大公约数
 * 如果q是0，那么最大公约数为p。
 * 否则，将p除以q得到余数r，p和q的最大公约数即为q和r的最大公约数
 * @Author zh2683
 */
public class Gcd {

    public static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args) {
        System.out.println(gcd(11, 2));
        System.out.println(gcd(100, 30));
        System.out.println(gcd(94 , 72));
    }
}
