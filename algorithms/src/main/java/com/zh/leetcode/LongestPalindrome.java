package com.zh.leetcode;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 *
 * 输入:
 * "abccccdd"
 *
 * 输出:
 * 7
 *
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome("abccccdd"));
    }

    public int longestPalindrome(String s) {
        char[] charArray = s.toCharArray();
        int[] counter = new int[58];
        for (char c : charArray) {
            counter[c - 'A'] += 1;
        }
        int length = 0;
         //只加偶数个的
        for (int i = 0 ; i < counter.length ; i++) {
            length += (counter[i] / 2) * 2;
        }
        // 如果最终长度比字符串长度小，说明肯定有奇数个的字母， 放在中间即可
        if (length < s.length()) {
            length++;
        }
        return length;
    }

}
