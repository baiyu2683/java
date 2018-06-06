package com.zh.typeinfo.pets;

/**
 * Created by zhangheng on 16-9-4.
 */
public class PetCount2 {
    public static void main(String[] args){
        PetCreator pc = Pets.creator;
        pc.types();
        PetCount.countPets(pc);
    }
}
