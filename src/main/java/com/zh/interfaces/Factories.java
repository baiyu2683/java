package com.zh.interfaces;

/**
 * Created by zh on 2017-04-13.
 */
public class Factories {
    public static void serviceConsumer(ServiceFactory factory) {
        Service s = factory.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
//        serviceConsumer(new Implementation1Factory());
//        serviceConsumer(new Implementation2Factory());
        serviceConsumer(Implementation1.factory);
        serviceConsumer(Implementation2.factory);
    }
}
interface Service {
    void method1();
    void method2();
}
interface ServiceFactory {
    Service getService();
}
class Implementation1 implements Service {
    Implementation1() {}
    public void method1() {
        System.out.println("Implementation1 method1");
    }
    public void method2() {
        System.out.println("Implementation1 method2");
    }
    //匿名内部类实现
    public static ServiceFactory factory = new ServiceFactory() {
        @Override
        public Service getService() {
            return new Implementation1();
        }
    };
}
class Implementation2 implements Service {
    Implementation2() {}
    public void method1() {
        System.out.println("Implementation2 method1");
    }
    public void method2() {
        System.out.println("Implementation2 method2");
    }
    public static ServiceFactory factory = new ServiceFactory() {
        @Override
        public Service getService() {
            return new Implementation2();
        }
    };
}
//class Implementation1Factory implements ServiceFactory {
//    public Service getService() {
//        return new Implementation1();
//    }
//}
//class Implementation2Factory implements ServiceFactory {
//    public Service getService() {
//        return new Implementation2();
//    }
//}
