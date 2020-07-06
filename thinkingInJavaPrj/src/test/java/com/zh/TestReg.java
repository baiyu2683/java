package com.zh;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
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

    @Test
    public void testRandN() {
        String text = null;
        try {
            text = IOUtils.toString(TestReg.class.getResourceAsStream("/testRandNText.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = text.replaceAll("[\\r\\n]", "<br/>")
                .replaceAll("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
        System.out.println(text);
    }

    @Test
    public void testPreAndAfterCondition() {
        String string = "'前\"后\"";
        // 前'开头，后"结尾
        String reg = "(?<=')([^\"]+?)(?=\")";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
        System.out.println("=============");
        // 前"开头，后"结尾
        reg = "(?<=\")([^\"]*?)(?=\")";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(string);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
        System.out.println("=============");
        // 前面没有'，后面有'
        reg = "(?<!\')([^\"\']*?)(?!\')";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(string);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
        System.out.println("=============");
    }

    @Test
    public void testReplaceWrap() {
        String value = "\"前\"";
        value = value.replaceAll("(\")([\\s\\S]*?)\\1", "$2");
        System.out.println(value);
    }

    @Test
    public void getScriptTagContent() throws IOException {
        String baiduHtml = IOUtils.toString(TestReg.class.getResourceAsStream("/getScriptTagContentTest.html"));
        String regStr = "<script[\\s\\S]*?>([\\s\\S]*?)</script>";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(baiduHtml);
        while (matcher.find()) {
            System.out.println("下一个===================================");
            System.out.println(matcher.group(1));
        }
    }
}
