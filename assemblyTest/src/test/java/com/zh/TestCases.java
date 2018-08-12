package com.zh;

import org.apache.xmlbeans.impl.regex.Match;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCases {

    @Test
    public void getMacAddress() throws UnknownHostException, SocketException {
        System.out.println(2 / 3f);
        System.out.println(Math.round(2 / 3f));
    }

    @Test
    public void bigDecimal() {
        BigDecimal bd = new BigDecimal(127.1123123d);
        System.out.println(bd.floatValue());
    }

    @Test
    public void insertWhiteSpace() {
        System.out.println("宋体".replace("", " ").trim());
    }

    @Test
    public void replaceTest() {
        String s = "";
        System.out.println(s);
        s = s.replace("<br>", "<br/>");
        Pattern pattern = Pattern.compile("(<img[^>]*?)>");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(1));
        }
        s = s.replaceAll("(<img[^>]*?)>", "$1/>");
        System.out.println(s);
    }

    @Test
    public void intConverter() {
        Double d = Double.valueOf("12.511");
        System.out.println((int)Math.round(d));
    }

    @Test
    public void splitTest() {
        String tagReg = "<(\\w*?) {0,}(.*?)>([\\w\\W]*?)</\\1>";
        String content = "font里 \n" +
                "<strike>\n" +
                "  asdf \n" +
                " <b> <u>混合1</u> asdf </b> \n" +
                "</strike>";
        String[] ss = content.split(tagReg);
        System.out.println(ss);
        Pattern pattern = Pattern.compile(tagReg);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            int groupCount = matcher.groupCount();
            System.out.println(matcher.group(0));
            if (groupCount > 0) {
                System.out.println("1:" + matcher.group(1));
            }
            if (groupCount > 1) {
                System.out.println("2:" + matcher.group(2));
            }
            if (groupCount > 2) {
                System.out.println("3:" + matcher.group(3));
            }
        }
    }
}
