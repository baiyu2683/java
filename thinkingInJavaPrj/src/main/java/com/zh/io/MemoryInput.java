package com.zh.io;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by zh on 2017-02-26.
 */
public class MemoryInput {

    public static void main(String[] args) throws IOException {
        String filePath = MemoryInput.class.getClassLoader().getResource("testRead.json").getFile();
        StringReader in = new StringReader(BufferedInputFile.read(filePath));
        int c;
        while((c = in.read()) != -1)
            System.out.println((char)c);
    }
}
