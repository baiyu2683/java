package com.zh.typeinfo.pets;

import java.util.ArrayList;

/**
 * Created by zhangheng on 16-9-4.
 */
public class Pets {
    public static final PetCreator creator = new LiteralPetCreator();

    public static Pet randomPet(){
        return creator.randomPet();
    }

    public static Pet[] createArray(int size){
        return creator.createArray(size);
    }

    public static ArrayList<Pet> arrayList(int size){
        return creator.arrayList(size);
    }
}
