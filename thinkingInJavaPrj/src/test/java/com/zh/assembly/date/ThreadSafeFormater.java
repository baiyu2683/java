package com.zh.assembly.date;

import java.time.Instant;

public class ThreadSafeFormater {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        long seconds = instant.getEpochSecond();
        System.out.println(seconds);
    }
}
