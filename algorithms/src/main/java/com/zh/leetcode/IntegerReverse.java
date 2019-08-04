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
public class IntegerReverse {

    public int reverse(int x) {
        // 位数
        int count = count(x);
        int tempCount = count;
        long result = 0;
        while (tempCount > 0) {
            long num = pow(10, tempCount - 1);
            result += (x / num) * (pow(10, count - tempCount));
            x = (int)(x % num);
            tempCount--;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
    }

    public int count(int x) {
        int i = 0;
        while (x / pow(10, i++) != 0) {
            // 最大10位
            if (i >= 10) {
                return 10;
            }
        }
        return i - 1;
    }

    private long pow(int src, int pow) {
        if (pow == 0) {
            return 1;
        }
        long tempSrc = src;
        for (int i = 2 ; i <= pow ; i++) {
            tempSrc *= src;
        }
        return tempSrc;
    }

    public static void main(String[] args) {
        IntegerReverse integerReverse = new IntegerReverse();
        System.out.println(integerReverse.reverse(Integer.MIN_VALUE));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(integerReverse.reverse(Integer.MAX_VALUE));
    }
}
