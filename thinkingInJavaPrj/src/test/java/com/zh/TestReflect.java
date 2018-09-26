package com.zh;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Consumer;

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

    @Test
    public void parameterizedTest() {
        ParameterizedTypeTest ptt = new ParameterizedTypeTest();
        Type[] types = ptt.getClass().getGenericInterfaces();
        if (types != null) {
            for (Type type : types) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                System.out.println(parameterizedType.getRawType());
                System.out.println(parameterizedType.getOwnerType());
                System.out.println(parameterizedType.getActualTypeArguments()[0]);
            }
        }
    }

    private interface Sign {}

    private class ParameterizedTypeTest implements Comparable<String>, Sign, Consumer<String>, List<String> {
        private String t;

        @Override
        public int compareTo(String o) {
            return 0;
        }

        @Override
        public void accept(String s) {

        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<String> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(String s) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends String> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends String> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public String get(int index) {
            return null;
        }

        @Override
        public String set(int index, String element) {
            return null;
        }

        @Override
        public void add(int index, String element) {

        }

        @Override
        public String remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<String> listIterator() {
            return null;
        }

        @Override
        public ListIterator<String> listIterator(int index) {
            return null;
        }

        @Override
        public List<String> subList(int fromIndex, int toIndex) {
            return null;
        }
    }
}


