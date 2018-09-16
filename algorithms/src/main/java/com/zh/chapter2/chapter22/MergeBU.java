package com.zh.chapter2.chapter22;

/**
 * 自底向上的归并排序
 * 首先两两归并(每个元素认为是一个数组)
 * 然后四四归并(每两个元素认为是一个数组)
 * ...
 * @author zh
 * 2018年9月16日
 */
public class MergeBU {
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int sz = 1; sz < N ; sz *= 2) {
			for (int lo = 0 ; lo < N - sz ; lo += sz * 2) {
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1,  N-1));
			}
		}
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int mid = (lo + hi) / 2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}
	
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		// 需要声明一个临时数组..,改进，可将次数组声明在sort中，作为参数传递进来
		Comparable[] aux = new Comparable[hi - lo + 1];
		int i = lo, j = mid + 1;
		for (int k = lo ; k <= hi ; k++) {
			aux[k] = a[k];
		}
		for (int k = lo ; k <= hi ; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(a[i], a[j])) a[k] = a[i++];
			else a[k] = a[j++];
		}
	}
	
	
}
