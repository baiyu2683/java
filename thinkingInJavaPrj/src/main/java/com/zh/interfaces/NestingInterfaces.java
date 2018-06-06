package com.zh.interfaces;

/**
 * Created by zh on 2017-04-13.
 */
public class NestingInterfaces {
    public class BImp implements A.B {
        public void f() {}
    }
    class CImp implements A.C {
        public void f() {}
    }
    //不能实现类中定义的私有接口
//    class DImp implements A.D {
//    }
    class EImp implements E {
        public void g() {}
    }
    class EGImp implements E.G {
        public void f() {}
    }
    class EImp2 implements E {
        public void g() {}
        class EG implements E.G {
            public void f() {}
        }
    }

    public static void main(String[] args) {
        A a = new A();
        A a2 = new A();
        //私有接口的实现类获取方法
        a2.receiveD(a.getD());
    }
}
class A {
    //包
    interface B {
        void f();
    }
    public class BImp implements B {
        public void f() {}
    }
    private class BImp2 implements B {
        public void f() {}
    }
    //公共
    public interface C {
        void f();
    }
    class CImp implements C {
        public void f() {}
    }
    private class CImp2 implements C {
        public void f() {}
    }
    //私有
    private interface D {
        void f();
    }
    private class DImp implements D {
        public void f() {}
    }
    public class DImp2 implements D {
        public void f() {}
    }
    public D getD() {
        return new DImp2();
    }
    private D dRef;
    public void receiveD(D d) {
        dRef = d;
        dRef.f();
    }
}
interface E {
    interface G {
        void f();
    }
    //Redundant "public"
    public interface H {
        void f();
    }
    void g();
    //接口中所有的域默认都是public的，不能再使用别的限定符
    //private interface I{}
}
