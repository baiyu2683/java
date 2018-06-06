package com.zh.enumerated;

import static com.zh.enumerated.Spiciness.*;
/**
 * enum可以静态导入
 * Created by zh on 2017-03-18.
 */
public class Burrito {
    Spiciness degree;
    public Burrito(Spiciness degree) {this.degree = degree;}
    public String toString() {return "Burrito is " + degree;}
    public static void main(String[] args) {
        System.out.println(new Burrito(NOT));
        System.out.println(new Burrito(MEDIUM));
        System.out.println(new Burrito(HOT));
        //但是同一个类中的enum必须用类名修饰
        System.out.println(Spiciness1.HOT);
    }
}
enum Spiciness1 {
    HOT,MEDIUM
}
