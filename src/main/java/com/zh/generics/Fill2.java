package com.zh.generics;

import com.zh.util.Generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zh on 2017-06-25.
 */
public class Fill2 {
    public static <T> void file(Addable<T> addable, Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; i++)
            try {
                addable.add(classToken.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    public static <T> void file(Addable<T> addable, Generator<T> generator, int size) {
        for(int i = 0; i < size; i++)
            addable.add(generator.next());
    }
}
class AddableCollectionAdapter<T> implements Addable<T> {
    private Collection<T> c;
    public AddableCollectionAdapter(Collection<T> c) {
        this.c = c;
    }
    public void add(T item) {c.add(item);}
}
class Adapter {
    public static <T> Addable<T> collectionAdapter(Collection<T> c) {
        return new AddableCollectionAdapter<>(c);
    }
}
interface Addable<T> {void add(T t);}

class Fill2Test {
    public static void main(String[] args) {
        List<Coffee> carrier = new ArrayList<>();
        Fill2.file(new AddableCollectionAdapter<>(carrier), Coffee.class, 3);
        for(Coffee c : carrier)
            System.out.println(c);
        System.out.println("-----------------------");
        //Use a adapted class;
    }
}
