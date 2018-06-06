package com.zh.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by zh on 2017-02-26.
 */
public class TextFile extends ArrayList<String> {

    //Read a file as a single string
    public static String read(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    stringBuilder.append(s);
                    stringBuilder.append(System.lineSeparator());
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
    //Write a single file in one method call
    public static void write(String fileName, String text) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName));
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //Read a file ,split by any regular expression;
    public TextFile(String fileName, String splitter) {
        super(Arrays.asList(read(fileName).split(splitter)));
        //Regular expression split() often leaves an empty String at the first position;
        if(get(0).equals("")) remove(0);
    }

    //Normally read by lines
    public TextFile(String fileName) {
        this(fileName, "\n");
    }

    public void write(String fileName) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                for(String item : this)
                    out.println(item);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String file = read("f:/test.txt");
        write("f:/test.json", file);
        TextFile textFile = new TextFile("f:/test.json");
        textFile.write("f:/text.txt");
        TreeSet<String> words = new TreeSet<>(new TextFile("f:/test.json", "\\W+"));
        System.out.println(words.headSet("a"));
    }
}
