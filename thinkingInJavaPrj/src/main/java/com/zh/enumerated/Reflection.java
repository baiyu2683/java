package com.zh.enumerated;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * values()方法是有编译器自动添加的
 * Created by zh on 2017-03-18.
 */
public class Reflection {
    public static Set<String> analyze(Class<?> enumClass) {
        System.out.println("-------- Analyzing " + enumClass + " --------");
        System.out.println("Interfaces");
        for(Type t : enumClass.getGenericInterfaces())
            System.out.println(t);
        System.out.println("Base: " + enumClass.getSuperclass());
        System.out.println("Methods: ");
        Set<String> methods = new TreeSet<>();
        //getDeclaredMethods会将从接口实现的方法也获得
//        for (Method m : enumClass.getDeclaredMethods())
//            methods.add(m.getName());
        for(Method m : enumClass.getMethods())
            methods.add(m.getName());
        System.out.println(methods);
        return methods;
    }
    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("Explore.containsAll(Enum)? " +
        exploreMethods.containsAll(enumMethods));
        System.out.print("Explore.removeAll(Enum): ");
        exploreMethods.removeAll(enumMethods);
        System.out.println(exploreMethods);
    }
}
enum Explore {
    HERE, THERE
}
