package com.zh.string;

public class TestStringBuilder {

    public static void main(String[] args) {
        String s = "\"第\" + @PageNO + \"页,共\" + @PageCount + \"页\"";
        s += "asdf";
        System.out.println(s);
    }
}
