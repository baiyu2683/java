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
        Pattern p = Pattern.compile("htwFileName=([^;]*);{0,1}");
        Matcher m = p.matcher("htwFileName=1234");
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
    
    @Test
    public void replaceAll() {
        Pattern p = Pattern.compile("htwFileName=([^;]*);{0,1}");
        System.out.println("asdf;htwFileName=234;".replaceAll(p.pattern(), ""));
    }

    @Test
    public void replaceAll2() {
        String url = "jdbc:mysql://127.0.0.1:10155/zsk?user=root&password=123456&useUnicode=true&characterEncoding=gb2312&autoReconnect=true&failOverReadOnly=false";
        System.out.println(url.replaceAll("(password=)([^&]*)&{0,1}", "$1******"));
    }
}
