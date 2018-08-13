package com.zh.chapter1.chapter13;

/**
 * 字符串定容栈
 * @Author zh2683
 */
public class FixedCapacityStackOfStrings {

    private String[] a; // stack entries
    private int N; // size

	public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        a[N++] = item;
    }
    
    public String pop() {
        return a[--N];
    }
    
    public boolean isFull() {
    	return N == a.length;
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings fixedCapacityStackOfStrings = new FixedCapacityStackOfStrings(100);
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
