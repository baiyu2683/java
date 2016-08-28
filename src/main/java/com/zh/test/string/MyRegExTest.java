package com.zh.test.string;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.org.glassfish.gmbal.AMXMetadata;
import com.zh.string.codewars.ArrayPrinter;
import com.zh.string.regex.MyRegEx;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
}
