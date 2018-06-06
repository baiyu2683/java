package com.zh.exceptions;

/**
 * TODO
 * run和debug打印的结果不一样？！！！
 * Created by zh on 2017-03-21.
 */
public class WhoCalled {
    static void f() {
        try {
            throw new Exception();
        } catch (Exception e) {
            for(StackTraceElement stackTraceElement : e.getStackTrace()) {
                System.out.println(stackTraceElement.getMethodName());
            }
        }
    }
    static void g(){
        f();
    }
    static void h(){
        g();
    }

    public static void main(String[] args) {
        f();
        System.out.println("-------------------------------");
        g();
        System.out.println("--------------------------------");
        h();
    }
}
