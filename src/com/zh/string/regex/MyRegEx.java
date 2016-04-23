package com.zh.string.regex;

import java.util.Arrays;

/**
 * Created by zhheng on 2016-04-23.
 */
public class MyRegEx {
    /**
     * 判断一个句子是否以大写字母开头，以句号结尾
     */
    public static boolean testPattern(String test) {
        String reg = "^[A-Z].*\\.$";
        return test.matches(reg);
    }
    /**
     * 将字符串在the和you处分隔
     */
    public static String[] testSplit(String test){
        //这里不能写成[the|you],中括号会匹配t h e y o u这些字母
        String reg = "(the|you)";
        return test.split(reg);
    }
    /**
     * 下划线替换所有元音字母：a e i o u A E I O U
     */
    public static String testReplace(String test){
        String reg = "[aeiouAEIOU]";
        return test.replaceAll(reg, "_");
    }
    /**
     * 找出所有不以大写字母开头的词，不重复的计算个数
     * TODO 表达式不对，没想明白，暂时搁置了。。
     */
    public static String[] exercise12(String test){
        String reg = "[^A-Z]\\w+";
        return test.split(reg);
    }
}
