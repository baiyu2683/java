package com.zh.test.string;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.org.glassfish.gmbal.AMXMetadata;
import com.zh.string.codewars.ArrayPrinter;
import com.zh.string.regex.MyRegEx;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhheng on 2016-04-23.
 */
public class MyRegExTest {
    @Before
    public void setUp(){
    }
    @Test
    public void test1(){
        Integer[] array=new Integer[]{2,4,5,2};
        assertEquals("2,4,5,2", ArrayPrinter.printArray(array));
        String[] s1 = new String[]{"1", "2", "3"};
        assertEquals("1,2,3",ArrayPrinter.printArray(s1));
    }
    @Test
    public void testPattern(){
        assertEquals(false, MyRegEx.testPattern(""));
        assertEquals(false, MyRegEx.testPattern(" "));
        assertEquals(false, MyRegEx.testPattern("A"));
        assertEquals(false, MyRegEx.testPattern("."));
        assertEquals(true, MyRegEx.testPattern("A."));
        assertEquals(false, MyRegEx.testPattern(".A"));
        assertEquals(true, MyRegEx.testPattern("Aasdfasdf asdfasdf ."));
        assertEquals(false, MyRegEx.testPattern("asdfasdf."));
        assertEquals(false, MyRegEx.testPattern("AAAAAA"));
    }

    @Test
    public void testSplit(){
        String s = "Then, when you have found trhe shrubbery, you must " +
                "cut down the mightiest tree int the forest... " +
                "with... a herring!";
        System.out.println(Arrays.toString(MyRegEx.testSplit(s)));
    }

    @Test
    public void testReplace(){
        String s = "Then, when you have found trhe shrubbery, you must " +
                "cut down the mightiest tree int the forest... " +
                "with... a herring!";
        System.out.println(MyRegEx.testReplace(s));
    }

    @Test
    public void test10(){
        String s = "Java now has regular expressions";
        String[] regs = {"^Java", "\\Breg*", "n.w\\s+h(a|i)s", "s?", "s*", "s+", "s{4}", "s{1}.", "s{0,3}"};
        for(String reg : regs){
            Pattern pattern = Pattern.compile(reg);
            Matcher m = pattern.matcher(s);
            if(m.find()){
                System.out.println(reg+"-"+true);
            }else{
                System.out.println(reg+"-"+false);
            }
        }
    }
    @Test
    public void test11(){
        String reg = "(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b";
        String s = "Arline ate eight apples and one orange while Anita hadn't any";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(s);
        while(m.find()){
            System.out.println("匹配到了。。。"+m.group());
        }
    }

    @Test
    public void test12(){
        Matcher m = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");
        while(m.find()){
            for(int j = 0; j <= m.groupCount(); j++)
                System.out.print(m.group(j) + "-");
            System.out.println();
        }
        System.out.println();
        int i = 0;
        while(m.find(i)){
            System.out.print(m.group()+"-");
            i++;
        }
    }
    @Test
    public void test13(){
        String s = "Twas brilling, and the slight toves\n"+
                "Did gyre and gimble\n"+
                "And asdf";
//        String[] arrs = MyRegEx.exercise12(s);
//        for(String ss : arrs){
//            System.out.print(ss+"-");
//        }
        String reg = "(^[a-z]|\\s+[a-z])\\w+";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(s);
        while(m.find()){
            System.out.println(m.group());
        }
    }

    @Test
    public void test15() {
        String reg = "a.*?b";
        String teststr = "addcddb";
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(teststr);
        while(matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    @Test
    public void test14() {
//        String s = "<li class=\"f_class_li first_li\" data-for=\"entertainment\"><a href=\"/f/index/forumpark?pcn=娱乐明星&amp;pci=0&amp;ct=1&amp;rn=20&amp;pn=1\" title=\"娱乐明星\" class=\"entertainment\">娱乐明星</a></li>"
//                + "<li class=\"f_class_li\" data-for=\"tvshows\"><a href=\"/f/index/forumpark?pcn=电视节目&amp;pci=0&amp;ct=1&amp;rn=20&amp;pn=1\" title=\"爱综艺\" class=\"tvshows\">爱综艺</a></li>"
//                + "<a class=\"class-item-title\" href=\"/f/index/forumpark?pcn=娱乐明星&amp;pci=0&amp;ct=1\">娱乐明星</a>"
//                + "<li><a href=\"/f/index/forumpark?cn=%E6%B8%AF%E5%8F%B0%E4%B8%9C%E5%8D%97%E4%BA%9A%E6%98%8E%E6%98%9F&amp;ci=0&amp;pcn=%E5%A8%B1%E4%B9%90%E6%98%8E%E6%98%9F&amp;pci=0&amp;ct=1\">港台东南亚明星</a></li>";
        File file = new File("F:/company/spider/贴吧分类.htm");
        StringBuilder s = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line=br.readLine()) != null) {
                s.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String a = "<a.*?href=([\"|\'])(/f/index/forumpark\\?pcn=.*?)\\1.*?>";//href=［\"|\'](/f/index/forumpark?pcn=.*?)[\"|\'].*?</a>$";
        Pattern pattern = Pattern.compile(a);
        Matcher matcher = pattern.matcher(s.toString());
        while(matcher.find()) {
            System.out.println(matcher.group(2));
        }
    }
}
