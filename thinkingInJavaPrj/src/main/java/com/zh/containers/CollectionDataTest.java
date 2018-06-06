package com.zh.containers;

import com.zh.util.Generator;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by zh on 2017-04-23.
 */
public class CollectionDataTest {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>(
                new CollectionData<>(new Government(), 15)
        );
        //Using the convenience method:
        set.addAll(CollectionData.list(new Government(), 15));
        System.out.println(set);
    }
}
class Government implements Generator<String> {
    String[] foundation = ("strange women lying in ponds distributing swords is no basis for a " +
            "system of government").split(" ");
    private int index;
    public String next() {
        return foundation[index++];
    }
}
