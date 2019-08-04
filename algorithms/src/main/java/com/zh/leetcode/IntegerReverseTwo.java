package com.zh.leetcode;

/**
 * 简单
 *
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 *
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class IntegerReverseTwo {

    public int reverse(int x) {
        // 位数
        long result = 0;
        while (x != 0) {
            result *= 10;
            result += x % 10;
            x /= 10;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        IntegerReverseTwo integerReverse = new IntegerReverseTwo();
        System.out.println(integerReverse.reverse(Integer.MIN_VALUE));
        System.out.println(integerReverse.reverse(Integer.MAX_VALUE));
        System.out.println(integerReverse.reverse(123));
        System.out.println(integerReverse.reverse(-12345));
    }
}
