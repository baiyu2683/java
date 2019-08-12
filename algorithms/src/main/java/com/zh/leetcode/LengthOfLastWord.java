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
 * 两个指针分别指向前后两个空格，从前往后遍历
 */
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        int result = 0;
        int tempResult = 0;
        int slow = -1;
        int fast = 0;
        int length = s.length();
        while (fast < length) {
            if (' ' == s.charAt(fast)) {
                tempResult = fast - slow - 1;
                if (tempResult > 0) {
                    result = tempResult;
                }
                slow = fast;
            }
            fast++;
        }
        if (slow < length - 1) {
            return fast - slow - 1;
        } else {
            return result;
        }
    }

    public static void main(String[] args) {
        LengthOfLastWord lengthOfLastWord = new LengthOfLastWord();
        System.out.println(lengthOfLastWord.lengthOfLastWord("helloworld"));
        System.out.println(lengthOfLastWord.lengthOfLastWord("word,  wsef   "));
        System.out.println(lengthOfLastWord.lengthOfLastWord("  wo,  wsef   "));
        System.out.println(lengthOfLastWord.lengthOfLastWord(" "));
        System.out.println(lengthOfLastWord.lengthOfLastWord("  "));
        System.out.println(lengthOfLastWord.lengthOfLastWord("   "));
    }
}
