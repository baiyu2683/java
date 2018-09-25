package com.zh.chapter2.chapter23;

import com.zh.chapter2.chapter21.InsertionAdvance;

import java.util.Arrays;

/**
 * 快速排序改进
 *
 * 三取样切分，取三个元素中位数切分
 *
 * @author zh
 * 2018年9月18日
 */
public class QuickAdvancedThreeMedian {

	private static Integer THREHOLD_SIZE = 8;

	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo + THREHOLD_SIZE - 1) {
			InsertionAdvance.sort(a, lo, hi);
			return;
		}
		Comparable pivot = threeMedian(a, lo, hi);
        int i = lo, j = hi - 1;
        while (true) {
            while (less(a[++i], pivot));
            while (less(pivot, a[--j]));
            if (i < j) {
                exch(a, i, j);
            } else break;
        }
        exch(a, i, hi - 1);

        sort(a, lo, i - 1);
        sort(a, i + 1, hi);
	}
	
	private static Comparable threeMedian(Comparable[] a, int lo, int hi) {
		int mid = (lo + hi) / 2;
		if (less(a[hi], a[mid])) exch(a, hi, mid);
		if (less(a[mid], a[lo])) exch(a, mid, lo);
		if (less(a[hi], a[mid])) exch(a, hi, mid);
		// 将标记放在最后一个
        exch(a, mid, hi - 1);
		return a[hi - 1];
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
}
