package com.zh.leetcode;

/**
 * 反给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 *  
 *
 * 示例：
 *
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *  
 *
 * 提示：
 *
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseWords {

    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        System.out.println(reverseWords.reverseWords("Let's take LeetCode contest"));
    }

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] ss = s.toCharArray();
        int left = 0;
        int right = 0;
        for (int i = 0 ; i < ss.length ; i++) {
            if (' ' == ss[i]) {
                right = i - 1;
                reverseWord(ss, left, right);
                left = i + 1;
            }
        }
        // 最后一个词
        reverseWord(ss, left, ss.length - 1);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < ss.length ; i++) {
            stringBuilder.append(ss[i]);
        }
        return stringBuilder.toString();
    }

    private void reverseWord(char[] words, int left, int right) {
        if (left >= right) {
            return;
        }
        while (true) {
            if (left < right) {
                char temp = words[left];
                words[left++] = words[right];
                words[right--] = temp;
            } else {
                break;
            }
        }
    }

}
