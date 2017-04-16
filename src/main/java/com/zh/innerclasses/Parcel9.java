package com.zh.innerclasses;

/**
 * 匿名内部类直接使用在其外部定义的对象时，这个参数必须时final，否则编译错误
 * 构造器不是直接使用
 * Created by zh on 2017-04-16.
 */
public class Parcel9 {
    public Destination destination(final String dest) {
        return new Destination() {
            private String label = dest;
            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Destination d = p.destination("123");
        System.out.println(d.readLabel());
    }
}
