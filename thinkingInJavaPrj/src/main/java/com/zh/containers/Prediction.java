package com.zh.containers;

import java.util.Random;

/**
 * Created by zh on 2017-04-23.
 * @see SpringDetector
 */
public class Prediction {
    private static Random random = new Random(47);
    private boolean shadow = random.nextDouble() > 0.5;
    public String toString() {
        if(shadow) return "Six more weeks of Winter!";
        else return "Early Spring!";
    }
}
