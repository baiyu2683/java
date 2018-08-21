package com.zh.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    /**
     * 根据正则表达式匹配字符串，并返回所有匹配的分组
     * @param regex
     * @param source
     * @return
     */
    public static List<String> match(String regex, String source) {
        Pattern pattern = Pattern.compile(regex );
        Matcher matcher = pattern.matcher(source);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                result.add(matcher.group(i));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String regex = "publishPath=[^;]*;{0,1}";
//        String s = "publishPath=";
//        String ss = "publishPath=@#KLJOPSD;other=asdf;publishPath=111;publishPath=112;publishPath=113;publishPath=114";
        String sss = "publishPath=@#KLJOPSDF>>[p..    a;other=asdf";
        System.out.println(match(regex, sss));
    }
}
