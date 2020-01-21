package com.zh.reflect.type;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TestType {

    @Test
    public void testTypeName() {
        // String
        System.out.println(String.class.getTypeName());
        // int
        System.out.println(int.class.getTypeName());
        // Integer
        System.out.println(Integer.class.getTypeName());
        // String[]
        System.out.println(String[].class.getTypeName());
    }

    @Test
    public void testGenericSuperclass() {
        // null
        System.out.println(int.class.getGenericSuperclass());
        // Number
        System.out.println(Integer.class.getGenericSuperclass());
        // Object
        System.out.println(Integer[].class.getGenericSuperclass());
    }

    /**
     * 数组才有作用
     */
    @Test
    public void testComponentType() {
        // null
        System.out.println(int.class.getComponentType());
        // int
        System.out.println(int[].class.getComponentType());
    }

    @Test
    public void testPackage() {
        System.out.println(int.class.getPackage());
        System.out.println(Integer.class.getPackage());
        System.out.println(Type.class.getPackage());
        System.out.println(Integer[].class.getPackage());
    }

    /**
     * @see Modifier
     * 每种修饰符定义了一个整形，多个叠加用或运算| ,整形定义按位分开的，因此多个值结果最后是和
     */
    @Test
    public void testModifier() {
        System.out.println(int.class.getModifiers());
        System.out.println(Integer.class.getModifiers());
    }

    @Test
    public void testToString() {
        System.out.println(int.class.toString());
        System.out.println(Integer.class.toString());
        System.out.println(Integer[].class.toString());
        System.out.println(int[].class.toString());
    }
}
