package com.zh;

/**
 * Created by zh on 2017-04-23.
 */
class C {
    C() {
        System.out.print("C");
    }
}

class A {
    C c = new C();

    A() {
        this("A");
        System.out.print("A");
    }

    A(String s) {
        System.out.print(s);
    }
}

class Test1 extends A {
    Test1() {
        super("B");
        System.out.print("B");
    }

    public void show() {
        System.out.println("A");
    }

    public static void main(String[] args) {
        Test1 t = new Test1();
        t.show();
    }
}
