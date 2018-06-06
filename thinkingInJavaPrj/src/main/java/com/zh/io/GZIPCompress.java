package com.zh.io;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 字符流读字节流写，中文出现乱码。。
 * @TODO 原因未知
 * Created by zh on 2017-03-12.
 */
public class GZIPCompress {
    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(
////                new FileReader("f:/test1.txt")
//                new InputStreamReader(new FileInputStream("f:/test1.txt"), "utf-8")
//        );
        BufferedInputStream inputStream = new BufferedInputStream(
                new FileInputStream("f:/test1.txt")
        );
        BufferedOutputStream outputStream = new BufferedOutputStream(
                new GZIPOutputStream(
                        new FileOutputStream("f:/test1.gz")
                )
        );
        System.out.println("Writing file");
        int c;
        while ((c = inputStream.read()) != -1)
            outputStream.write(c);
//        in.close();
        inputStream.close();
        outputStream.close();
        System.out.println("Reading file");
        BufferedReader in2 = new BufferedReader(
                new InputStreamReader(
                        new GZIPInputStream(
                                new FileInputStream("f:/test1.gz")
                        ),
                "utf-8")
        );
        String s;
        while((s = in2.readLine()) != null)
            System.out.println(s);
    }
}
