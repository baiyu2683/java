package com.zh.containers.testframework;

import org.junit.runners.parameterized.TestWithParameters;

/**
 * Created by zh on 2017-04-23.
 */
public abstract class Test<C> {
    String name;
    public Test(String name) {
        this.name = name;
    }
    public abstract int test(C container, TestParam tp);
}
