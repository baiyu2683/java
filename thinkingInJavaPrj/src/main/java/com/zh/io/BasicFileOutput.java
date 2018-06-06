package com.zh.io;

import java.io.*;
import java.util.stream.Stream;

/**
 * Created by zh on 2017-02-26.
 */
public class BasicFileOutput {
    public static void main(String[] args) throws IOException {
        String result = BufferedInputFile.read(BasicFileOutput.class.getClassLoader().getResource("testRead.json").getFile());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 100; i++)
            sb.append(result);
//        BufferedReader in = new BufferedReader(
//                new StringReader(sb.toString())
//        );

        LineNumberReader in = new LineNumberReader(
                new StringReader(sb.toString())
        );

        long start = System.currentTimeMillis();
        PrintWriter out = new PrintWriter(
            new BufferedWriter(new FileWriter("F:/test.json"))
        );
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(in.getLineNumber() + ": " + s);
        }
        out.close();
        System.out.println(System.currentTimeMillis() - start);
    }
}
