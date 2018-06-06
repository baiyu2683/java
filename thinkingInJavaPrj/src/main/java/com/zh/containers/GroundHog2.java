package com.zh.containers;

/**
 * Created by zh on 2017-04-23.
 */
public class GroundHog2 extends Groundhog {
    public GroundHog2(int n) {
        super(n);
    }
    public int hashCode() { return number; }
    public boolean equals(Object o) {
        return o instanceof GroundHog2 && (number == ((GroundHog2)o).number);
    }
}
