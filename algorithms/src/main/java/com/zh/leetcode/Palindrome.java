package com.zh.leetcode;

/**
 * 简单
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * <b>解题思路: 首先算出来数字位数，然后取每一位的数字和对应位数的数字进行比较，如果不相等说明不是回文数字</b>
 */
public class Palindrome {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int numberCount = count(x);
        int i = 1;
        while (i <= numberCount / 2) {
            int indexNumberLow = indexNumber(x, i);
            int indexNumberHight = indexNumber(x, numberCount - i + 1);
            if (indexNumberLow != indexNumberHight) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * x的位数
     * @param x
     * @return
     */
    private int count(int x) {
        int result = 0;
        while (x != 0) {
            result++;
            x /= 10;
        }
        return result;
    }

    /**
     * 获取x中第index位的数字
     * 1234 -> 2 -> 3
     * @param x
     * @param index  最低位为1
     * @return
     */
    private int indexNumber(int x, int index) {
        int result = 0;
        while (index != 0) {
            result = x % 10;
            x = x / 10;
            index--;
        }
        return result;
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindrome(123321));
        System.out.println(palindrome.isPalindrome(2212122));
        System.out.println(palindrome.isPalindrome(Integer.MAX_VALUE));
        System.out.println(palindrome.isPalindrome(Integer.MIN_VALUE));
    }
}
