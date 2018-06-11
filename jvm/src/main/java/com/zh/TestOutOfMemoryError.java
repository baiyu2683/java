package com.zh;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestOutOfMemoryError {
    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        System.out.println(dateFormat.format(new Date()));
    }

    public void show() {
        try {
            throw new Error();
        } finally {
            System.out.println(111);
        }
    }
}
