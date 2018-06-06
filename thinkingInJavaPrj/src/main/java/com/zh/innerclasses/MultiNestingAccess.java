package com.zh.innerclasses;

/**
 * Created by zh on 2017-04-16.
 */
public class MultiNestingAccess {
    public static void main(String[] args) {
        MMA mma = new MMA();
        MMA.A a = mma.new A();
        MMA.A.B b = a.new B();
        b.h();
    }
}
class MMA {
    private void f(){
        System.out.println("f()");
    }
    class A {
        private void g() {
            System.out.println("g()");
        }
        public class B {
            void h() {
                g();;
                f();
                System.out.println("h()");
            }
        }
    }
}
