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

    @Test
    public void testRandN() {
        String text = ">0：字体映射器转换这个值以设备单位，并和已有字体的单元高度相匹配。\n" +
                "0：字体映射器转换在选择匹配时用一个缺省的高度值。\n" +
                "<0：字体映射器转换这个值到设备单位，并将它的绝对值和已有字体的字符高度相匹配。\n" +
                "比較全部的高度，字体映射器选择不超过要求大小的最大字体。\n" +
                "此映射当字体第一次被使用时发生。\n" +
                "对于MM_TEXT映射方式，能够用以下的公式为一种指定了点大小的字体确定高度：\n" +
                "nHeight=-MulDiv(PointSize, GetDeviceCaps(hDC, LOGPIXELSY),72)\n" +
                "nWidth：指定所要求字体的字符的逻辑单位的平均宽度。假设此值为0，字体映射器选择一个closest match值。closest match值是由比較当前设备的特征系数与可使用字体的数字化特征\n" +
                "系数之差的绝对值而确定的。\n" +
                "nEscapement：指定移位向量和设备X轴之间的一个角度。以十分之中的一个度为单位。\n" +
                "移位向量平行于正文行的基线。";
        text = text.replaceAll("[\\r\\n]", "<br/>")
                .replaceAll("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
        System.out.println(text);
    }
}
