package com.zh.collection;

import java.util.BitSet;

public class TestBitSet {

    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(2, true);
        bitSet.set(3, false);
        System.out.println(bitSet.get(1));
        System.out.println(bitSet.get(2));
        System.out.println(bitSet.get(3));
    }
}
