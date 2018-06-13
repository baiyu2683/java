package com.zh;


import java.io.File;

public class JavaEnvTest {

    public static void main(String[] args) {
        System.out.println(File.pathSeparator);
        System.out.println(File.separator);
        System.out.println(File.pathSeparatorChar);
        System.out.println(File.separatorChar);
        String s = "sadfasdf;asdfasd;asdf";
        System.out.println(s.substring(0, s.lastIndexOf(";")));
    }
}
