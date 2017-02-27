package com.zh.io;

import java.io.*;

/**
 * Created by zh on 2017-02-27.
 */
public class Redirecting {
    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("f:/test.json"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("f:/test.out")));
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = br.readLine()) != null)
            System.out.println(s);
        out.close(); //!!!!!!!!!!
        System.setOut(console);
    }
}
