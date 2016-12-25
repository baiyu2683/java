package com.zh.concurrent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zh on 2016/12/25.
 */
public class Exercise14 {
    private static volatile int i = 0;
    public static int getValue(){ return i++; }
    public static void main(String[] args) {
        for(int i = 0; i < 10000000; i++) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println(getValue());
                }
            }, 5000);
        }
    }
}
