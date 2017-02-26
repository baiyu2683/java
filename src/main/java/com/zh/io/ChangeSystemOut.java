package com.zh.io;

import java.io.PrintWriter;

/**
 * Created by zh on 2017-02-27.
 */
public class ChangeSystemOut {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello, world");
    }
}
