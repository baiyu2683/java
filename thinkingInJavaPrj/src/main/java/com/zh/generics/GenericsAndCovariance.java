package com.zh.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * ？通配符表示任意类型，用于容器类型中时不能放除了null之外的任意类型
 * Created by zh on 2017-06-18.
 */
public class GenericsAndCovariance {
    public static void main(String[] args) {
        //下面此种用法，不能放入向flist中放入任何对象
        //但是可以遍历
        List<? extends Fruit> flist = new ArrayList<>();
//        flist.add(new Fruit());
//        flist.add(new Apple());
        List<Apple> alist = new ArrayList<>();
        alist.add(new Apple());
        alist.add(new Apple());

        flist = alist;
        for (Fruit fruit : flist) {
            System.out.println(fruit.show());
        }

//        List<?> l = new ArrayList<>();
//        l.add(null);
//        System.out.println(l.size());
    }
}
