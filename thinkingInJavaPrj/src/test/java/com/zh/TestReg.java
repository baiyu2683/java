package com.zh;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Author: zh <br/>
 * Date: 2018-08-06 <br/>
 */
public class TestReg {

    @Test
    public void main() {
        String html = "asdfasdf<br/>asdfasdf<br  />ao9weopiqweoi<br >";
        String[] htmls = html.split("<br\\s{0,}/{0,1}>");
        System.out.println(Arrays.toString(htmls));
    }
    
    /**
     * appendReplacementAPI 使用
     */
    @Test
    public void appendReplacement() {
        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat two cats in the yard");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            System.out.println(m.start());
            System.out.println(m.end());
            m.appendReplacement(sb, "dog");
            System.out.println(sb.toString());
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
