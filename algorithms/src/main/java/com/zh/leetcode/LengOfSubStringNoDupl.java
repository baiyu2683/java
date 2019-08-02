package com.zh.leetcode;

/**
 *  字符串中最大无重复子串长度
 * @author Administrator
 * @date 2019/08/02
 */
public class LengOfSubStringNoDupl {

    public static void main(String[] args) {
        LengOfSubStringNoDupl lengOfSubStringNoDupl = new LengOfSubStringNoDupl();
        String s = "";
        System.out.println(lengOfSubStringNoDupl.lengthOfLongestSubstring(s));
        s = "aaaaa";
        System.out.println(lengOfSubStringNoDupl.lengthOfLongestSubstring(s));
        s = "asdfasdf";
        System.out.println(lengOfSubStringNoDupl.lengthOfLongestSubstring(s));
        s = "abcdefghijklmn9opqrstuvwxyz";
        System.out.println(lengOfSubStringNoDupl.lengthOfLongestSubstring(s));
    }


    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0;
        int end = 1;
        int max = end - start;
        for (int i = 1 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            if (contains(s, start, end, c)) {
                // 计算长度
                if (end - start > max) {
                    max = end - start;
                }
                do {
                    start++;
                } while (contains(s, start, end, c));
            } else {
                int length = end - start + 1;
                if (length > max) {
                    max = length;
                }
            }
            end++;
        }
        return max;
    }

    private boolean contains(String string, int start, int end, char cr) {
        if (start >= end) {
            return false;
        }
        for (int i = start ; i < end ; i++) {
            char content = string.charAt(i);
            if (cr == content) {
                return true;
            }
        }
        return false;
    }
}
