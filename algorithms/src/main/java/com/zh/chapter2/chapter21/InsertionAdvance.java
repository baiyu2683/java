package com.zh.chapter2.chapter21;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
import utils.IntGenerator;

/**
 * 改进之前的插入排序: 每次只是将元素向后移动而不交换位置，只在找到位置后赋值<br/>
 * 
 * <b>注意第0个元素的赋值</b>
 * 
 * @see Insertion
 * 
 * @author zh
 * 2018年9月13日
 */
public class InsertionAdvance {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1 ; i <= hi ; i++) {
            Comparable temp = a[i];
            int j = i - 1;
            for (; j >= lo ; j--) {
                if (less(temp, a[j])) a[j + 1] = a[j];
                else {
                    a[j + 1] = temp;
                    break;
                }
            }
            if (j == lo - 1) a[lo] = temp;
        }
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private static void show(Comparable[] a) {
        System.out.println(Arrays.toString(a));
    }
    
    public static boolean isSorted(Comparable[] a) {
        for (int i = 0 ; i < a.length ; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
//        Integer[] a = IntGenerator.generatorIntegerArr(300);
        int N = 10;
        Double[] a = new Double[N];
        for (int i = 0 ; i < N ; i++) {
            a[i] = StdRandom.uniform();
        }
        sort(a,2, 8);
        assert isSorted(a);
        show(a);
    }
}
