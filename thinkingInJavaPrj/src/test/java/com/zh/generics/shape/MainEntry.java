package com.zh.generics.shape;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 ? extend xxx 和 ? super xxx
 * PECS原则
 */
public class MainEntry {

    public static void main(String[] args) {
        List<? extends Shape> list = new ArrayList<>();
        list = new ArrayList<Square>(); // list可能指向一个Square列表
        list = new ArrayList<Triangle>(); // list可能指向一个Triangle列表
        // 由于list可能指向任何Shape子类，但是类型擦除后不能确定是具体的那种类型，因此下面的add不成立
//        list.add(new Square());
    }
}
