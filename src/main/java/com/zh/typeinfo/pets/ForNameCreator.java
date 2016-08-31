package com.zh.typeinfo.pets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangheng on 16-8-31.
 */
public class ForNameCreator extends PetCreator {
    private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();

    private static String[] typeNames = {
            "com.zh.typeinfo.pets.Mutt",
            "com.zh.typeinfo.pets.Pug",
            "com.zh.typeinfo.pets.EgyptianMau",
            "com.zh.typeinfo.pets.Manx",
            "com.zh.typeinfo.pets.Cymric",
            "com.zh.typeinfo.pets.Rat",
            "com.zh.typeinfo.pets.Mouse",
            "com.zh.typeinfo.pets.Hamster"
    };

    private static void loader(){
        try{
            for(String name : typeNames){
                types.add((Class<? extends Pet>)Class.forName(name));
            }
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    static{loader();}

    public List<Class<? extends Pet>> types(){
        return types;
    }

}
