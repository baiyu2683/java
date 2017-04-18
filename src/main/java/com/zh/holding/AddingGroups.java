package com.zh.holding;

import java.util.*;

/**
 * Created by zh on 2017-04-18.
 */
public class AddingGroups {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1,2,3,4));
        Integer[] moreInts = {6,7,8,9};
        collection.addAll(Arrays.asList(moreInts));
        Collections.addAll(collection, 11, 22, 33, 44);
        List<Integer> list = Arrays.asList(12,13,14,15);
        list.set(1, 22);
        System.out.println(list);
    }
}
