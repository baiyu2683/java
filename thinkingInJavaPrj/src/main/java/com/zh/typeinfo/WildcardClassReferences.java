package com.zh.typeinfo;

/**
 * Created by zhangheng on 16-8-31.
 */
public class WildcardClassReferences {
    public static void main(String[] args){
        Class<?>  intClass = int.class;
        intClass = double.class;         //由于使用了泛型，表示非具体的类型，所以可以指向其他的类引用
    }
}
