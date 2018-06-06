package com.zh.enumerated;

import java.util.Random;

/**
 * Created by zh on 2017-03-19.
 */
interface Item {
    Outcome compete(Item it);
    Outcome eval(Paper p);
    Outcome eval(Scissors p);
    Outcome eval(Rock p);
}
class Paper implements Item {

    @Override
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return Outcome.DRAW;
    }

    @Override
    public Outcome eval(Scissors p) {
        return Outcome.WIN;
    }

    @Override
    public Outcome eval(Rock p) {
        return Outcome.LOSE;
    }
}
class Scissors implements Item {

    @Override
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return Outcome.LOSE;
    }

    @Override
    public Outcome eval(Scissors p) {
        return Outcome.DRAW;
    }

    @Override
    public Outcome eval(Rock p) {
        return Outcome.WIN;
    }
}
class Rock implements Item {

    @Override
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return Outcome.WIN;
    }

    @Override
    public Outcome eval(Scissors p) {
        return Outcome.LOSE;
    }

    @Override
    public Outcome eval(Rock p) {
        return Outcome.DRAW;
    }
}
public class RoShamBo1 {
    static final int SIZE = 20;
    private static Random random = new Random(47);
    public static Item newItem() {
        switch (random.nextInt(3)) {
            default:
            case 0:return new Scissors();
            case 1:return new Paper();
            case 2:return new Rock();
        }
    }
    public static void match(Item a, Item b) {
        System.out.println(a + " vs. " + b + ": " + a.compete(b));
    }

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++)
            match(newItem(), newItem());
    }
}
