package com.zh.typeinfo;

/**
 * Created by zhangheng on 16-8-31.
 */
public class GenericClassReferences {

    public static void main(String[] args){
        Class intClass = int.class;
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class;
        intClass = double.class;               //普通的类可以重新赋值为其他的Class对象
        //genericIntClass = double.class;        //泛型类引用不能赋值为其他的Class对
        //Class<Number> genericNumberClass = int.class;

        //通配符， 使用非具体的类型
        Class<?> intClass1 = int.class;
        intClass1 = double.class;                    //正确的
        System.out.println(intClass1);
    }

}
