package com.zh.chapter3;

import java.util.LinkedList;

public class BinarySearchST {

    private Integer[] keys;
    private String[] values;
    private int N;

    public BinarySearchST(int capacity) {
        keys = new Integer[capacity];
        values = new String[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回比key小的元素的个数
     * @param key
     * @return
     */
    public int rank(Integer key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public String get(Integer key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return values[i];
        }
        return null;
    }

    public void put(Integer key, String value) {
        // 查找键是否存在
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
        }
        for (int j = N ; j > i ; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public void delete(Integer key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            for (int j = i + 1 ; j < N ; j++) {
                keys[j - 1] = keys[j];
                values[j - 1] = values[j];
            }
            keys[N - 1] = null;
            values[N - 1] = null;
            N--;
        }
    }

    public Integer min() {
        return keys[0];
    }

    public Integer max() {
        return keys[N - 1];
    }

    public Integer select(int k) {
        return keys[k];
    }

    public Integer ceiling(Integer key) {
        int i = rank(key);
        return keys[i];
    }

    public Integer floor(Integer key) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return keys[i];
        }
        return keys[i - 1];
    }

    public boolean contains(Integer key) {
        return get(key) != null;
    }

    public Iterable<Integer> keys(Integer lo, Integer hi) {
        int lin = rank(lo);
        int hin = rank(hi);
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = lin ; i < hin ; i++) {
            result.add(keys[i]);
        }
        // rank返回的是比hi小的元素的个数，hin可能为N
        if (contains(hi)) {
            result.add(keys[hi]);
        }
        return result;
    }

    public static void main(String[] args) {
        BinarySearchST binarySearchST = new BinarySearchST(10);
//        binarySearchST.put(1, "2");
//        binarySearchST.put(2, "3");
//        binarySearchST.put(0, "4");
        System.out.println(binarySearchST.rank(5));
        binarySearchST.delete(1);
    }
}
