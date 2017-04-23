package com.zh.containers;

/**
 * Created by zh on 2017-04-23.
 * @see SpringDetector
 */
public class Groundhog {
    protected int number;
    public Groundhog(int n) {
        number = n;
    }
    public String toString() {
        return "Groundhog #" + number;
    }
}
