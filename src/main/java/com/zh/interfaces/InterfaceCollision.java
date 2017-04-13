package com.zh.interfaces;

/**
 * 验证类和接口的名字相同但是返回类型或签名不同时的情形
 * Created by zh on 2017-04-13.
 */
public class InterfaceCollision {
}
interface I1 {void f();}
interface I2 {int f(int i);}
interface I3 {int f();}

class C {
    public int f() {
        return 1;
    }
}
class C2 implements I1, I2 {
    @Override
    public void f() {

    }

    public int f(int i) {
        return 1;
    }
}

class C3 extends C implements I2 {
    public int f(int i) { return 1; }
}

class C4 extends C implements I3 {
    public int f() { return 1; }
}
//仅通过返回类型无法区分两个重载方法
//class C5 extends C implements I1 {}
//interface I4 extends I1, I3 {}
//