package com.zh.innerclasses;

/**
 * 继承内部类
 * Created by zh on 2017-04-16.
 */
public class InheritInner extends WithInner.Inner {
    //构造其不能只传内部类，并且需要调用通过super
    InheritInner(WithInner wi) {
        wi.super(); //这样调用后导出类InheritInner中重新持有WithInner的引用
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner i = new InheritInner(wi);
        InheritInner2 i2 = new InheritInner2(wi, "s");
        System.out.println();
    }
}
class WithInner {
    class Inner {};
    class Inner2 {
        private String label;
        Inner2(String label) {
            this.label = label;
        }
    }
}
//继承内部类Inner2
class InheritInner2 extends WithInner.Inner2 {
    InheritInner2(WithInner wi, String label) {
        wi.super(label);
    }
}
