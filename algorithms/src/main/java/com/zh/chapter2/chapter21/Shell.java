package com.zh.chapter2.chapter21;

import utils.IntGenerator;

/**
 * 希尔排序
 * @author zh
 * 2018年9月12日
 */
public class Shell {

	public static void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		// h为a长度的三分之一
		while (h < N / 3)
			h = 3 * h + 1; // 1, 4, 13, 40, 121...
		while (h >= 1) {
			// 步长为h的插入排序
			for (int i = h ; i < N ; i++) {
				for (int j = i ; j >= h && less(a[j], a[j-h]) ; j -= h) {
					exch(a, j, j-h);
				}
			}
			h /= 3;
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
		for (int i = 0 ; i < a.length ; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static boolean isSorted(Comparable[] a) {
		for (int i = 0 ; i < a.length ; i++) {
			if (less(a[i], a[i-1])) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Integer[] a = IntGenerator.generatorIntegerArr(200);
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
