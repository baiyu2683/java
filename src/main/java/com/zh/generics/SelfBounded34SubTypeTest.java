package com.zh.generics;

/**
 * Created by zh on 2017-06-25.
 */
public class SelfBounded34SubTypeTest {
    public static void main(String[] args) {
        SubType34 subType34 = new SubType34();
        SubType34 subType341 = (subType34);
        subType341.setElement(subType34);
        System.out.println(subType341);
    }
}
class SubType34 extends SelfBounded34<SubType34> {

    @Override
    public void show(SubType34 arg) {
        System.out.println(arg);
    }
}
