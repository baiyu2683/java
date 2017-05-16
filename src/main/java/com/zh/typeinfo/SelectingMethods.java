package com.zh.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zh on 2017-05-14.
 */
public class SelectingMethods {
    public static void main(String[] args) {
        SomeMethods proxy = (SomeMethods) Proxy.newProxyInstance(
                SomeMethods.class.getClassLoader(),
                new Class[]{SomeMethods.class},
                new MethodSelector(new Implementation())
        );
        proxy.boring1();
        proxy.boring2();
        proxy.interresting("bonobo");
        proxy.boring3();
    }
}
class MethodSelector implements InvocationHandler {

    private Object proxied;
    public MethodSelector(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("interresting"))
            System.out.println("Proxy detected the interesting method");
        return method.invoke(proxied, args);
    }
}
interface SomeMethods {
    void boring1();
    void boring2();
    void interresting(String arg);
    void boring3();
}
class Implementation implements SomeMethods {
    @Override
    public void boring1() {
        System.out.println("boring1");
    }

    @Override
    public void boring2() {
        System.out.println("boring2");
    }

    @Override
    public void interresting(String arg) {
        System.out.println("interresting " + arg);
    }

    @Override
    public void boring3() {
        System.out.println("boring3");
    }
}
