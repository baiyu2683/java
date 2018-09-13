package com.zh.chapter2.chapter21;

import utils.IntGenerator;

/**
 * 插入排序
 * 使用哨兵来规避边界测试,可以去掉内循环的j>0
 * @author zh
 * 2018年9月11日
 */
public class InsertionSentry {

	public static void sort(Comparable[] a) {
		// 升序
		int N = a.length;
		// 首先找到最小的元素放在最左边
		int min = 0;
		for (int i = 0 ; i < N ; i++) {
			if (less(a[i], a[min])) min = i;
		}
		exch(a, 0, min);
		for (int i = 1 ; i < N ; i++) {
			for (int j = i ; less(a[j], a[j - 1]) ; j--) {
					exch(a, j, j-1);
			}
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
