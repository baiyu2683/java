package com.zh.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by zh on 2017-02-26.
 */
public class BinaryFile {

    public static byte[] read(File bFile) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(bFile));
        try {
            byte[] data = new byte[bufferedInputStream.available()]; //文件过大怎么办
            bufferedInputStream.read(data);
            return data;
        } finally {
            bufferedInputStream.close();
        }
    }
    public static byte[] read(String bFile) throws IOException {
        return read(new File(bFile).getAbsoluteFile());
    }
}
