package com.zh.innerclasses;

/**
 * Created by zh on 2017-04-16.
 */
public class MultiImplementation {
    static void takesD(D d) {}
    static void takesE(E e) {}
    public static void main(String[] args) {
        Z z = new Z();
        takesD(z);
        takesE(z.makeE());
    }
}
//z必须继承d和e两个类，只有内部类才能是实现
class D {}
abstract class E {}
class Z extends D {
    E makeE() {
        return new E() {};
    }
}
