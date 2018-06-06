package com.zh.innerclasses;

/**
 * .new语法用来告知某些其他对象，去创建某个内部类的对象
 * Created by zh on 2017-04-16.
 */
public class DotNew {
    public class Inner {}

    public static void main(String[] args) {
        DotNew dn = new DotNew();
        DotNew.Inner dni = dn.new Inner();
    }
}
