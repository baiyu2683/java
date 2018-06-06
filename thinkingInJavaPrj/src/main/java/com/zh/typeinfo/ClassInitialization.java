package com.zh.typeinfo;

import java.util.Random;

/**
 * Created by zhangheng on 16-8-28.
 */

public class ClassInitialization {
    public static Random random = new Random(47);

    public static void main(String[] args){
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");
        // Does not trigger initialization
        System.out.println(Initable.staticFinal);
        //Does trigger initialization
        System.out.println();
        System.out.println(Initable.staticFinal2);
        System.out.println("-----------------------------");
        // Does trigger initialization:
        System.out.println(Initable2.staticNonFinal);
        try {
            System.out.println("-----------------------------");
            Class initable3 = Class.forName("com.zh.typeinfo.Initable3");
            System.out.println("After creating Initable3 ref");
            System.out.println(Initable3.staticNonFinal);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class Initable{
    static final int staticFinal = 47;              //这是一个编译期常量，类不进行初始化就可以访问，
    static final int staticFinal2 = ClassInitialization.random.nextInt(1000); //类必须进行初始化才能访问
    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2{
    static int staticNonFinal = 147;
    static{
        System.out.println("Initializing Initable2");
    }
}

class Initable3{
    static int staticNonFinal = 74;
    static{
        System.out.println("Initializing Initable3");
    }
}
