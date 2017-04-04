package com.zh.polymorphic;

/**
 * Created by zh on 2017-04-04.
 */
public class PrivateOverride {
    private void f() {
        System.out.println("private f()");
    }

    public static void main(String[] args) {
        //向上转型，但无法覆盖基类的私有方法
        PrivateOverride privateOverride = new Derived();
        privateOverride.f();
    }
}

class Derived extends PrivateOverride {
    public void f() {
        System.out.println("public f()");
    }
}