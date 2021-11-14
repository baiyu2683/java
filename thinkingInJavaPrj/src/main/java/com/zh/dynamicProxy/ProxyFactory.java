package com.zh.dynamicProxy;



import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static <T> T object(T object) {
        return object(object, false);
    }

    public static <T> T object(T object, boolean forceProxy) {
        Class clazz = object.getClass();
        Class[] interfaces = clazz.getInterfaces();
        ClassLoader classLoader = clazz.getClassLoader();
        if (forceProxy) {
            return proxy(classLoader, interfaces, new MyProxy(object));
        }
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == ProxyAnno.class) {
                return proxy(classLoader, interfaces, new MyProxy(object));
            }
        }
        return object;
    }

    private static <T> T proxy(ClassLoader classLoader, Class[] interfaces, InvocationHandler invocationHandler) {
        return (T) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }
}
