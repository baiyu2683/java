package com.zh.dynamicProxy;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ProxyTest {

    @Test
    public void test() {
        Consumer<String> consumer = ProxyFactory.object(new MyClass());
        consumer.accept("10");
        consumer = ProxyFactory.object(new MyClassNoProxy());
        consumer.accept("10");

        List<String> list = ProxyFactory.object(new ArrayList<>(), true);
        list.add("10");
        list.get(0);
    }

    class MyClassNoProxy implements Consumer<String> {

        @Override
        public void accept(String s) {
            System.out.println("MyClassNoProxy");
        }
    }

    @ProxyAnno("这是一个可怜的类")
    class MyClass implements Consumer<String> {

        @Override
        public void accept(String s) {
            System.out.println("myClass内部");
            System.out.println(this.getClass().getSimpleName());
        }
    }
}
