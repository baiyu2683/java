package com.zh.generics;

import com.zh.typeinfo.pets.Dog;
import com.zh.typeinfo.pets.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 受检查的容器
 * Created by zh on 2017-06-25.
 */
public class CheckedList {
    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List probablyDogs) {
        probablyDogs.add(new Cat());
    }

    public static void main(String[] args) {
        List<Dog> dogs1 = new ArrayList<>();
        oldStyleMethod(dogs1);
        List<Dog> dog2 = Collections.checkedList(new ArrayList<Dog>(), Dog.class);
        try {
            oldStyleMethod(dog2);
        } catch (Exception e) {
            System.out.println(e);
        }
        List<Pet> pets = Collections.checkedList(new ArrayList<Pet>(), Pet.class);
        pets.add(new Dog());
        pets.add(new com.zh.typeinfo.pets.Cat());
    }
}
