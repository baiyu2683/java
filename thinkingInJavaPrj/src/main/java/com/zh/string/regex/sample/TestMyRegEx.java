package com.zh.string.regex.sample;

import com.zh.string.regex.MyRegEx;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangheng on 2016/10/17.
 */
public class TestMyRegEx {

    @Test
    public void test1(){
        Pattern pattern = Pattern.compile("(\\S)");
        Matcher matcher = pattern.matcher("1 2345");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test2() {
        String s = "http://127.0.0.1:1231/asdada.htm " +
                "http://localhost:1231/asdada.htm " +
                "http://www.baidu.com/asdada.htm " +
                "http://www.sina.com.cn/asdada.htm " +
                "http://127.0.0.1:9999/asdada.htm ";
        Pattern pattern = Pattern.compile("http://(.*?)/");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    /**
     * 匹配所有<a开头的行
     */
    @Test
    public void test3() {
        String s = "<a>            </ul>                                    </div>        <div class=\"p_thread thread_theme_5\" id=\"thread_theme_5\"><div class=\"l_thread_info\"><ul class=\"l_posts_num\">\n" +
                "\t<li class=\"l_pager pager_theme_4 pb_list_pager\"><span class=\"tP\">1</span>\n" +
                "<a href=\"/p/2717273749?pn=2\">2</a>\r\n" +
                "<a href=\"/p/2717273749?pn=3\">3</a>\n" +
                "<a href=\"/p/2717273749?pn=4\">4</a>\n" +
                "<a href=\"/p/2717273749?pn=5\">5</a>\n" +
                "s\n" +
                "<a href=\"/p/2717273749?pn=6\">6</a>\n" +
                "<a href=\"/p/2717273749?pn=2\">下一页</a>\n" +
                "<a href=\"/p/2717273749?pn=6\">尾页</a>\n" +
                "</li>";
//        s = "<aasdfasdfasdf";
        Pattern pattern = Pattern.compile("^<a.*?$",Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        String ss = s.replaceAll("(?m)^[s<].*?$", "123");
        System.out.println(ss);
    }

    /**
     * 匹配整个字符串
     */
    @Test
    public void test4() {
        String s = "<a>            </ul>                                    </div>        <div class=\"p_thread thread_theme_5\" id=\"thread_theme_5\"><div class=\"l_thread_info\"><ul class=\"l_posts_num\">\n" +
                "\t<li class=\"l_pager pager_theme_4 pb_list_pager\"><span class=\"tP\">1</span>\n" +
                "<a href=\"/p/2717273749?pn=2\">2</a>\r\n" +
                "<a href=\"/p/2717273749?pn=3\">3</a>\n" +
                "<a href=\"/p/2717273749?pn=4\">4</a>\n" +
                "<a href=\"/p/2717273749?pn=5\">5</a>\n" +
                "s\n" +
                "<a href=\"/p/2717273749?pn=6\">6</a>\n" +
                "<a href=\"/p/2717273749?pn=2\">下一页</a>\n" +
                "<a href=\"/p/2717273749?pn=6\">尾页</a>\n" +
                "</li>";
//        s = "<aasdfasdfasdf";
        Pattern pattern = Pattern.compile("[\\s\\S]*");
        Matcher matcher = pattern.matcher(s);
        if(matcher.find())
            System.out.println(matcher.group());
    }

    /**
     * 替换空行
     */
    @Test
    public void replaceBlankLine() {
        String s = "asdf\n" +
                "\n" +
                "zhagheng\n";
        s = s.replaceAll("(?m)^$", "");
        System.out.println(s);
    }

    @Test
    public void test5() {
        String s = "Quansar\n" +
                "Qasdf\n" +
                "qsdaf\n" +
                "q123\n" +
                "wdsdf\n" +
                "kkd\n" +
                "iiie\n" +
                "asdf\n" +
                "iasdfqiii\n" +
                "Iraq";
        Pattern pattern = Pattern.compile("q[^u]");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find())
            System.out.println(matcher.group());
    }

    /**
     * 为数字插入逗号分隔符
     */
    @Test
    public void test6() {
        String s = "12345611990";
        String reg = "(?<=\\d)(?=\\d{3}+$)";
        reg = "(?<=\\d)(?=(\\d{3})+$)";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.groupCount());
        }
    }

    @Test
    public void test7() {
        int num = 0;
        String s = "asdf\n" +
                "     \n" +
                "dddddfa    \n" +
                "#\n" +
                "asdf\n";
        Pattern pattern = Pattern.compile("(?<hh>a+)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.start() + "-" + matcher.end());
            System.out.println(matcher.group("hh"));
        }
        System.out.println(Pattern.quote("\\s*"));
        s = s.replaceAll(Pattern.quote("\n"), "");
        System.out.println(s);
    }

    @Test
    public void test8() {
        String s = "(\\w+)";
        s.replace("", "");
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher("a sdf asdfa sdf");
        matcher.reset("as df");//重置
        while (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(1));
        }
    }
}
