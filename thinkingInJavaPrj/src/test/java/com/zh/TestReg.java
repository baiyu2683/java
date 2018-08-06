package com.zh;

import java.util.Arrays;

/**
 * Author: Administrator <br/>
 * Date: 2018-08-06 <br/>
 */
public class TestReg {

    public static void main(String[] args) {
        String html = "asdfasdf<br/>asdfasdf<br  />ao9weopiqweoi<br >";
        String[] htmls = html.split("<br\\s{0,}/{0,1}>");
        System.out.println(Arrays.toString(htmls));
    }
}
