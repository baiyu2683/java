package com.zh.innerclasses;

/**
 * Created by zh on 2017-04-16.
 */
public class MultiInterfaces {
    static void takesA(A a) {}
    static void takesB(B b) {}

    public static void main(String[] args) {
        X x = new X();
        Y y = new Y();
        takesA(x);
        takesA(y);
        takesB(x);
        takesB(y.makeB());
    }
}
//都是接口，可以像X那样直接实现两个接口实现多重继承
//也可以像Y那样通过内部类实现多重继承
interface A {}
interface B {}
class X implements A, B {}
class Y implements A {
    B makeB() {
        return new B() {};
    }
}