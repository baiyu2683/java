package com.zh.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy implements InvocationHandler {

    private Object obj;

    public MyProxy (Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我们这是一个代理对象");
        Object res = method.invoke(obj, args);
        System.out.println("在调用之后打印一些内容");
        return res;
    }

}
