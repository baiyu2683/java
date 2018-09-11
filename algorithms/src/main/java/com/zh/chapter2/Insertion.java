package com.zh.chapter2;

import utils.IntGenerator;

/**
 * 插入排序
 * 平均情况(所有元素随机排列): N^2/4次比较，N^2/4次交换
 * 最坏(逆序): N^2/2次比较，N^2/2次交换
 * 最好(有序): N次比较，0次交换 
 * @author zh
 * 2018年9月11日
 */
public class Insertion {

	public static void sort(Comparable[] a) {
		// 升序
		int N = a.length;
		for (int i = 1 ; i < N ; i++) {
			// 将a[i] 插入a[i-1]、a[i-2]... 之中
			for (int j = i ; j > 0 ; j--) {
				if (less(a[j], a[j - 1]))
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
