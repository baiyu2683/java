package com.zh.innerclasses;

/**
 * Created by zh on 2017-04-16.
 */
public class Parcel10 {
    public Destination destination(final String dest, final float price) {
        return new Destination() {
            private int cost;
            //如下实例初始化相当于构造器，但是不能重载
            {
                cost = Math.round(price);//四舍五入
                if(cost > 100)
                    System.out.println("Over budget!");
            }
            private String label = dest;
            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel10 p = new Parcel10();
        Destination d = p.destination("123", 101.533f);
        System.out.println(d.readLabel());
    }
}

