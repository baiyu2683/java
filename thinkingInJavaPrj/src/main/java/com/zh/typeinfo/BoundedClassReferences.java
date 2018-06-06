package com.zh.typeinfo;

/**
 * Created by zhangheng on 16-8-31.
 */
public class BoundedClassReferences {
    public static void main(String[] args){
        Class<? extends Number> bounded = int.class;       //通配符？配合extends限定范围
        bounded = double.class;
        bounded = Number.class;
        //...anything else derived from Number
        System.out.println(bounded);
    }
}
