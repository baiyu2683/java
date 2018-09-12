package com.zh.chapter2.chapter21;

import utils.IntGenerator;

/**
 * 选择排序，N^2/2次比较，N次交换
 * 两个特点: 
 * 1. 运行时间和输入无关。无论输入有序或者无序，排序时间都是一样的
 * 2. 数据移动是最少的。比较N次，移动N次，和元素个数成线性关系
 * @author zh
 * 2018年9月11日
 */
public class Selection {

	public static void sort(Comparable[] a) {
		// 将a[]按升序排列
		int N = a.length;
		for (int i = 0 ; i < N ; i++) {
			//将a[i] 和 a[i+1] - a[N - 1] 中最小的元素交换
			int min = i;
			for (int j = i + 1 ; j < N ; j++) {
				if (less(a[j], a[min])) min = j;
			}
			exch(a, i, min);
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
