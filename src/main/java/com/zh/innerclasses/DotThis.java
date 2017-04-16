package com.zh.innerclasses;

/**
 * .this语法用于在内部类中获得外部类的引用
 * Created by zh on 2017-04-16.
 */
public class DotThis {
    void f() {
        System.out.println("DotThis.f()");
    }
    public class Inner {
        public DotThis outer() {
            return DotThis.this; //对外部类的引用, 直接写this是对内部类本身的引用
        }

        void f() {
            System.out.println("Inner().f()");
        }
    }
    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.f();
        dti.outer().f();
    }
}
