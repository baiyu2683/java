package com.zh.innerclasses;

/**
 * 在方法中定义一个局部内部类
 * Created by zh on 2017-04-16.
 */
public class Parcel5 {
    public Destination destination(String s) {
        //定义在方法中的局部内部类
        class PDestination implements Destination {
            private String label;
            private PDestination(String whereTo) {
                this. label = whereTo;
            }
            public String readLabel() {
                return label;
            }
        }
        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("Henam");
    }
}
