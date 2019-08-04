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
 * 解题思路： 将数字反转，如果和原来的数字相等就是回文数字
 */
public class PalindromeTwo {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int tempX = x;
        int result = 0;
        while (x != 0) {
            result *= 10;
            result += x % 10;
            x /= 10;
        }
        return result == tempX;
    }

    public static void main(String[] args) {
        PalindromeTwo palindrome = new PalindromeTwo();
        System.out.println(palindrome.isPalindrome(123321));
        System.out.println(palindrome.isPalindrome(2212122));
        System.out.println(palindrome.isPalindrome(Integer.MAX_VALUE));
        System.out.println(palindrome.isPalindrome(Integer.MIN_VALUE));
    }
}
