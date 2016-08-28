package com.zh.typeinfo;

import java.lang.reflect.Field;

/**
 * Created by zhangheng on 16-8-28.
 */
public class CommonUtils {
    /**
     * 递归打印一个类所有的继承类
     */
    public static void printInherit(Object o){
        Class clazz = o.getClass();
        printSuperClass(clazz);
    }

    private static void printSuperClass(Class c){
        Class up = c.getSuperclass();
        if(up != null){
            System.out.println("CanonicalName:" + up.getCanonicalName());
            printSuperClass(up);
        }
    }

    public static void printField(Object o){
        Class clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getName() + "-" + field.getGenericType() + "-" + field.getType() + "-" + field.getModifiers());
        }
    }
}
