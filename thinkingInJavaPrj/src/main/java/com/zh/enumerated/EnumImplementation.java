package com.zh.enumerated;

import com.zh.util.Generator;

import java.util.Random;

/**
 * Created by zh on 2017-03-18.
 */
public class EnumImplementation {

    public static <T> void printNext(Generator<T> generator) {
        System.out.print(generator.next() + ", ");
    }

    public static void getNext() {
        System.out.print(CartoonCharacter.getNext() + ", ");
    }

    public static void main(String[] args) {
        CartoonCharacter cartoonCharacter = CartoonCharacter.BOB;
        for (int i = 0; i < 10; i++)
            printNext(cartoonCharacter); //需要一个CartoonCharacter实例
        System.out.println();
        for (int i = 0; i < 10; i++)
            getNext(); //不需要新建实例
    }
}

enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
    private static Random random = new Random(47);
    @Override
    public CartoonCharacter next() {
        return values()[random.nextInt(values().length)];
    }

    public static CartoonCharacter getNext() {
        return values()[random.nextInt(values().length)];
    }
}
