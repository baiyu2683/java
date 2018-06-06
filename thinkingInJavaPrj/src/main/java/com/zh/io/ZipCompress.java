package com.zh.io;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * Created by zh on 2017-03-12.
 */
public class ZipCompress {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("f:/test.zip");
        CheckedOutputStream checkedOutputStream =
                new CheckedOutputStream(fileOutputStream, new Adler32());
        ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);
        zipOutputStream.setComment("A test of Java Zipping");
        BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(zipOutputStream);
        //No corresponding getComment(), though.
        String[] files = new String[]{"f:/temp.tmp", "f:/test1.txt", "f:/test2.txt"};
        for (String file : files) {
            System.out.println("Writing file : " + file);
//            BufferedReader in = new BufferedReader(new FileReader(file));
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            zipOutputStream.putNextEntry(new ZipEntry(file));
            int c;
            while ((c = in.read()) != -1){
                bufferedOutputStream.write(c);
            }
            in.close();
            bufferedOutputStream.flush();
        }
        bufferedOutputStream.close();
        System.out.println("Checksum: " + checkedOutputStream.getChecksum().getValue());
        //Now extract the files
        System.out.println("Reading file");
        FileInputStream fileInputStream = new FileInputStream("f:/test.zip");
        CheckedInputStream checkedInputStream =
                new CheckedInputStream(fileInputStream, new Adler32());
        ZipInputStream inputStream = new ZipInputStream(checkedInputStream);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ZipEntry zipEntry;
        while ((zipEntry = inputStream.getNextEntry()) != null) {
            System.out.println("Reading file " + zipEntry);
            int x;
            while ((x = bufferedInputStream.read()) != -1)
                System.out.write(x);
        }
        System.out.println("Checksum:" + checkedInputStream.getChecksum().getValue());
        bufferedInputStream.close();
        ZipFile zipFile = new ZipFile("f:/test.zip");
        Enumeration e = zipFile.entries();
        while (e.hasMoreElements()) {
            ZipEntry zipEntry1 = (ZipEntry) e.nextElement();
            System.out.println("File:" + zipEntry1);
        }
    }
}
