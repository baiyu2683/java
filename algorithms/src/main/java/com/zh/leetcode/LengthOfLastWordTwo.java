package com.zh.leetcode;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 *
 * 示例:
 *
 * 输入: "Hello World"
 * 输出: 5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/length-of-last-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 从后往前遍历找到地一个不是空格的字符，然后从此索引再往前遍历找到第一个空格, 根据两个索引计算
 */
public class LengthOfLastWordTwo {

    public int lengthOfLastWord(String s) {
        int left = s.length() - 1;
        int right = left;
        while (left >= 0 && s.charAt(left) == ' ') {
            left--;
        }
        if (left < 0) {
            return 0;
        }
        right = left;
        while (left >= 0 && s.charAt(left) != ' ') {
            left--;
        }
        return right - left;
    }

    public static void main(String[] args) {
        LengthOfLastWordTwo lengthOfLastWord = new LengthOfLastWordTwo();
        System.out.println(lengthOfLastWord.lengthOfLastWord("helloworld"));
        System.out.println(lengthOfLastWord.lengthOfLastWord("word,  wsef   "));
        System.out.println(lengthOfLastWord.lengthOfLastWord("  wo,  wsef   "));
        System.out.println(lengthOfLastWord.lengthOfLastWord(" "));
        System.out.println(lengthOfLastWord.lengthOfLastWord("  "));
        System.out.println(lengthOfLastWord.lengthOfLastWord("   "));
    }
}
