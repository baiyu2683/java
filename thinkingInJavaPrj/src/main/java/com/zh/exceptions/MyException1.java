package com.zh.exceptions;

/**
 * 父类方法声明了异常，但是子类继承时可以不写，编译不会有问题
 * Created by zh on 2017-03-21.
 */
public class MyException1 extends MyExceptionParent {
    @Override
    public void show(){
        System.out.println("123");
    }

    public static void main(String[] args) {
        new MyException1().show();
    }
}
