package com.zh.polymorphic;

/**
 * Created by zh on 2017-04-09.
 */
public class RTTI {
    public static void main(String[] args) {
        Useful[] x = {
                new Useful(),
                new MoreUseful()
        };
        x[0].f();
        x[1].g();
        ((MoreUseful)x[1]).u();
        ((MoreUseful)x[0]).u(); //ClassCastException
    }
}
class Useful {
    public void f() {}
    public void g() {}
}
class MoreUseful extends Useful {
    public void f() {}
    public void g() {}
    public void u() {}
    public void v() {}
    public void w() {}
}