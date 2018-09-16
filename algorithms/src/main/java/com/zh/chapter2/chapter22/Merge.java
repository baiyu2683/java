package com.zh.chapter2.chapter22;

/**
 * 归并排序
 * 最坏情况下比较次数是NlogN
 * 最好的(排好序的)是N
 * 
 * 额外空间为N
 * @author zh
 * 2018年9月16日
 */
public class Merge {
	
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, 0, a.length - 1, aux);
	}
	
	private static void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
		if (hi <= lo) return;
		int mid = (lo + hi) / 2;
		sort(a, lo, mid, aux);
		sort(a, mid + 1, hi, aux);
		merge(a, lo, mid, hi, aux);
	}
	
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
		// 需要声明一个临时数组..
//		Comparable[] aux = new Comparable[hi - lo + 1];
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
