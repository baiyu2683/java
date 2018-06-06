package com.zh.exceptions;

/**
 * 自定义一个简单异常，继承异常Exception类
 * Created by zh on 2017-03-21.
 */
public class InheritingExceptions {
    public void f() throws SimpleException {
        System.out.println("Throw SimpleException from f()");
        throw new SimpleException();
    }

    public static void main(String[] args) {
        InheritingExceptions sed = new InheritingExceptions();
        try {
            sed.f();
        } catch (SimpleException e) {
            System.out.println("Caught it");
        }
    }
}

class SimpleException extends Exception {

}
