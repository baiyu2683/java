package com.zh.containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zh on 2017-04-23.
 */
public class FillingLists {
    public static void main(String[] args) {
        List<StringAddress> list = new ArrayList<>(
                //将对象复制n份
                Collections.nCopies(4, new StringAddress("Hello"))
        );
        System.out.println(list);
        //将整个列表填充为某个对象
        Collections.fill(list, new StringAddress("World!"));
        System.out.println(list);
    }
}
class StringAddress{
    private String s;
    public StringAddress(String s) {
        this.s = s;
    }
    public String toString() {
        return super.toString() + " " + s;
    }
}
