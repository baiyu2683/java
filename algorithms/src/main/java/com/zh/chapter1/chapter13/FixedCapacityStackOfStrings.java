package com.zh.chapter1.chapter13;

/**
 * @Author zh2683
 */
public class FixedCapacityStackOfStrings<Item> {

    private Item[] a; // stack entries
    private int N; // size

    public FixedCapacityStackOfStrings(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        a[N++] = item;
    }

    public Item pop() {
        return a[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings<String> fixedCapacityStackOfStrings = new FixedCapacityStackOfStrings(100);
        String[] ss = {"1", "3", "5", "7", "-", "89", "23", "-"};
        for (String item : ss) {
            if (!item.equals("-")) {
                fixedCapacityStackOfStrings.push(item);
            } else {
                System.out.println(fixedCapacityStackOfStrings.pop() + " ");
            }
        }
        System.out.println(fixedCapacityStackOfStrings.size() + " left on stack.");
    }
}
