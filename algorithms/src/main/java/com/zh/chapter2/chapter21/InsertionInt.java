package com.zh.chapter2.chapter21;

import utils.IntGenerator;

/**
 * 插入排序
 * 原始类型int
 * @author zh
 * 2018年9月11日
 */
public class InsertionInt {

	public static void sort(int[] a) {
		// 升序
		int N = a.length;
		for (int i = 1 ; i < N ; i++) {
			// 将a[i] 插入a[i-1]、a[i-2]... 之中
			for (int j = i ; j > 0 && less(a[j], a[j - 1]) ; j--) {
//				if (less(a[j], a[j - 1]))
					exch(a, j, j-1);
//				else break;
			}
		}
	}
	
	private static boolean less(int v, int w) {
		return v < w;
	}
	
	private static void exch(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	private static void show(int[] a) {
		for (int i = 0 ; i < a.length ; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static boolean isSorted(int[] a) {
		for (int i = 0 ; i < a.length ; i++) {
			if (less(a[i], a[i-1])) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[] a = IntGenerator.generatorIntArr(200);
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
