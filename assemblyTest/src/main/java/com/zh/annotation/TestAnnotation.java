package com.zh.annotation;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TestAnnotation {

    @Test
    public void test1() {

    }

    static class A {

    }


    @Retention(RetentionPolicy.RUNTIME)
    static @interface Bean {

    }
}
