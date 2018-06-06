package com.zh.io;

import java.io.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zh on 2017-02-26.
 */
public class Echo {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("F:/test.txt"));
        String s;
        while((s = bufferedReader.readLine()) != null && s.length() != 0)
            System.out.println(s.toUpperCase());
        //An empty line terminates the program
    }
}
