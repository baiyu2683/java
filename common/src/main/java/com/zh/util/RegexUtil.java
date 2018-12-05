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
    public static List<String> match(String source, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                result.add(matcher.group(i));
            }
        }
        return result;
    }

    /**
     * regex匹配source中内容
     * @param regex
     * @param source
     * @return
     */
    public static boolean contains(String source, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}
