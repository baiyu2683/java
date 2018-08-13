package com.zh;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Random;

import org.junit.Test;

/**
 * Author: Administrator <br/>
 * Date: 2018-07-16 <br/>
 */
public class TestReflect {

    /**
     * 测试反射获取参数名称
     * @throws NoSuchMethodException
     */
    @Test
    public void main() throws NoSuchMethodException {
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
    
    /**
     * 测试属性访问，无set方法注入
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    @Test
    public void testReflectAccessProperty() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String ss = "123";
        class TestClazz {
            private String prop;
            
            public String toString() {
                return "prop: " + prop + ", " + ss + ", ";
            }
        }
        
        TestClazz tc = new TestClazz();
        Field field = tc.getClass().getDeclaredField("prop");
        System.out.println(field.getModifiers());
        field.setAccessible(true);
        field.set(tc, "123");
        // 打印发现prop已经被赋值成功了
        System.out.println(tc);
    }

}


