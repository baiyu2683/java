package com.zh.innerclasses;

/**
 * Created by zh on 2017-04-16.
 */
public class Outer {
    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        //Contents具有包访问权限，同包下的lei可以访问，不同的不能访问
        Parcel2.Contents c = p.contents();
        Parcel3 parcel3 = new Parcel3();
        Parcel3.Destination d = parcel3.new Destination("Beijing");
    }
}
