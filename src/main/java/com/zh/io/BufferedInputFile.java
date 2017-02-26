package com.zh.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by zh on 2017-02-26.
 */
public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while((s = br.readLine()) != null) {
            sb.append(s + System.lineSeparator());
        }
        br.close();
        return sb.toString();
    }

    public static String readReverse(String filename, String word) throws IOException {
        LinkedList<String> result = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while((line = br.readLine()) != null) {
            if(line.contains(word))
                result.addFirst(line + System.lineSeparator());
        }
        return result.stream().reduce("", (pre, next) -> pre.toUpperCase() + next.toUpperCase());
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        URL url = BufferedInputFile.class.getClassLoader().getResource("testRead.json");
        System.out.println(url.getFile());
//        System.out.println(read("testRead.json"));
        System.out.println(readReverse(url.getFile(), ""));
    }
}
