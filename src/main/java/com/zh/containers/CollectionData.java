package com.zh.containers;

import com.zh.util.Generator;

import java.util.ArrayList;

/**
 * Created by zh on 2017-04-23.
 */
public class CollectionData<T> extends ArrayList<T> {
    public CollectionData(Generator<T> generator, int quantity) {
        for (int i = 0; i < quantity; i++)
            add(generator.next());
    }
    public static <T> CollectionData<T> list(Generator<T> generator, int quantity) {
        return new CollectionData<T>(generator, quantity);
    }
}
