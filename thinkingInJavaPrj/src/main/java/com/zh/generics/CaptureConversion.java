package com.zh.generics;

/**
 * 捕获转换
 * Created by zh on 2017-06-19.
 */
public class CaptureConversion {
    static <T> void f1(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getSimpleName());
    }
    static void f2(Holder<?> holder) {
        f1(holder); //Call with captured type
    }

    public static void main(String[] args) {
        Holder raw = new Holder<Integer>(1);
        f1(raw);
        f2(raw);
        Holder rawBasic = new Holder();
        rawBasic.set(new Object());
        f2(rawBasic);
        Holder<?> wildcarded = new Holder<Double>(1.0);
        f2(wildcarded);
    }
}
