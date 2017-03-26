package com.zh.exceptions;

/**
 * Created by zh on 2017-03-26.
 */
class OneException extends Exception {
    public OneException(String s) {
        super(s);
    }
}
class TwoException extends Exception {
    public TwoException(String s) {
        super(s);
    }
}
public class RethrowNew {
    public static void f() throws OneException {
        System.out.println("originating the exception in f()");
        throw new OneException("thrown form f()");
    }

    public static void main(String[] args) {
        try {
            try {
                f();
            } catch (OneException e) {
                System.out.println("Caught in inner try, e.printStackTrace()");
                e.printStackTrace(System.out);
                throw new TwoException("from inner try");//重新抛出异常，但是原异常信息丢失
                //通过initCause(e)方法将异常信息保存下来,形成异常链
//                throw (TwoException)new TwoException("from inner try").initCause(e);
            }
        } catch (TwoException e) {
            System.out.println("Caught in outer try, e.printStackTrace()");
            e.printStackTrace(System.out);
        }
    }
}

