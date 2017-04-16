package com.zh.innerclasses;

/**
 * Created by zh on 2017-04-16.
 */
public class InnerExc26 {
    public static void main(String[] args) {
        //依赖的外部类
        WithInner1 withInner1 = new WithInner1();
        //新建内部类
        WithInner1.Inner1 inner1 = withInner1.new Inner1("hh");
        //继承时需要将内部类的外部类引用传入
        WithInner2.Inner2 inner2 = new WithInner2().new Inner2(withInner1, "ss");
    }
}

class WithInner1 {
    class Inner1 {
        private String label;
        Inner1(String s) {
            label = s;
            System.out.println("s:" + s);
        }
    }
}
class WithInner2 {
    class Inner2 extends WithInner1.Inner1 {
        Inner2(WithInner1 wi1, String s) {
            wi1.super(s);
        }
    }
}
