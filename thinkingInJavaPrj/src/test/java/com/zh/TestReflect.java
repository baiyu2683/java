package com.zh;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Random;

/**
 * Author: Administrator <br/>
 * Date: 2018-07-16 <br/>
 */
public class TestReflect {

    public static void main(String[] args) throws NoSuchMethodException {
        A a = new A();
        Method method = A.class.getDeclaredMethod("random", int.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter.getName());
        }
    }

    public static class A {

        public int random(int seed) {
            return new Random(seed).nextInt(47);
        }
    }

}


