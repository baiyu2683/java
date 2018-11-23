package com.zh;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("qwer");
        ListIterator<String> listIterator = list.listIterator();
        System.out.println(listIterator.next());
        listIterator.add("456");
        System.out.println(list);
    }
}
