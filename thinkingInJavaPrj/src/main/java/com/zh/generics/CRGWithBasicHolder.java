package com.zh.generics;

/**
 * Created by zh on 2017-06-25.
 */
public class CRGWithBasicHolder {
    public static void main(String[] args) {
        Subtype st1 = new Subtype(), st2 = new Subtype();
        st1.set(st2);
        Subtype st3 = st1.get();
        st1.f();
    }
}
class Subtype extends BasicHolder<Subtype> {}
