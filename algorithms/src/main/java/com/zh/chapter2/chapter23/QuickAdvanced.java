package com.zh.chapter2.chapter23;

import com.zh.chapter2.chapter21.InsertionAdvance;

/**
 * 快速排序改进
 *
 * 基于下面两点:
 * 1. 对于小数组，快速排序比插入排序慢
 * 2. 因为递归，快速排序的sort()方法在小数组中也会调用自己
 *
 * @author zh
 * 2018年9月18日
 */
public class QuickAdvanced {

	private static Integer THREHOLD_SIZE = 8;

	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		if (hi <= lo + THREHOLD_SIZE - 1) {
			InsertionAdvance.sort(a, lo, hi);
			return;
		}
		// 分片，左边比j小，右边比j大
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Comparable v = a[lo];
		while (true) {
			while (less(a[++i], v)) if (i == hi) break;
			while (less(v, a[--j])) if (j == lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j); // 第二个循环，j肯定比v小
		return j;
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
