package com.zh.innerclasses;

/**
 * Created by zh on 2017-04-16.
 */
public class Parcel7 {
    public Contents contents() {
        return new Contents() {
            int i = 11;
            @Override
            public int value() {
                return i;
            }
        };
    }

    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
        System.out.println(c.value());
    }
}
