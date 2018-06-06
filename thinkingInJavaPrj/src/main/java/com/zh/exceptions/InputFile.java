package com.zh.exceptions;

import com.zh.io.BufferedInputFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by zh on 2017-03-26.
 */
public class InputFile {
    private BufferedReader in;
    public InputFile(String fileName) throws Exception {
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Could not open " + fileName);
            //Wasn't open, so don't close it
            throw e;
        } catch (Exception e) {
            //All other exceptions must close it
            try {
                in.close();
            } catch (IOException e1) {
                System.out.println("in.close() unsuccessful");
            }
            throw e;//rethrow
        } finally {
            //Don't close it here!!!
        }
    }
    public String getLine() {
        String s;
        try {
            s = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException("readLine() failes");
        }
        return s;
    }
    public void dispose() {
        try {
            in.close();
            System.out.println("dispose() successful");
        } catch (IOException e2) {
            throw new RuntimeException("in.close() failed");
        }
    }
}
