package com.zh.chapter2.chapter23;

/**
 * 快速排序
 * @author zh
 * 2018年9月18日
 */
public class Quick {

	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		Comparable v = a[lo];
		while(true) {
			while(less(a[++i], v)) if (i == hi) break; // 找到一个比v大的
			while(less(v, a[--j])) if (j == lo) break; // 找到一个比v小的
			if (i >= j) break; // 当i>=j时，结束
			exch(a, i, j); // 交换
		}
		exch(a, lo, j);
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
