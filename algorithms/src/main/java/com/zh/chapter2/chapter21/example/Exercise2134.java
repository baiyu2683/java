package com.zh.chapter2.chapter21.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;
import com.zh.chapter1.chapter14.StopWatch;
import com.zh.chapter2.chapter21.Insertion;
import com.zh.chapter2.chapter21.InsertionAdvance;
import com.zh.chapter2.chapter21.InsertionInt;
import com.zh.chapter2.chapter21.InsertionSentry;
import com.zh.chapter2.chapter21.Selection;
import com.zh.chapter2.chapter21.Shell;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 各种极端输入的情况下排序算法
 * 1.数组有序
 * 2.数组逆序
 * 3.数组所有元素相同
 * 4.数组只含有两种元素
 * @author zh
 * 2018年9月12日
 */
public class Exercise2134 {
	
	public static double time(String situation, String alg, int N) {
		Double[] a = null;
		if (situation.equals("sort")) a = sortedArr(N);
		if (situation.equals("reverse")) a = reverseArr(N);
		if (situation.equals("one")) a = oneEleArr(N);
		if (situation.equals("two")) a = twoEleArr(N);
		if (situation.equals("zeroOneAndTwo")) a = zeroOneAndTwo(N);
		if (situation.equals("zeroAndRandom")) a = zeroAndRandom(N);
		return sort(alg, a);
	}
	
	public static double sort(String alg, Double[] a) {
		StopWatch sw = new StopWatch();
		if (alg.equals("Insertion")) Insertion.sort(a);
		if (alg.equals("InsertionAdvance")) InsertionAdvance.sort(a);
		if (alg.equals("Selection")) Selection.sort(a);
		if (alg.equals("Shell")) Shell.sort(a);
		return sw.elapseMillTime();
	}
	
	public static double timeRandomInput(String situation, String alg, int N, int T) {
		// 使用算法alg 将T个长度为N的输入排序
		double total = 0.0;
		Double[] a = new Double[N];
		for (int t = 0 ; t < T ; t++) {
			total += time(situation, alg, N);
		}
		return total;
	}
	//1.有序数组
	private static Double[] sortedArr(int N) {
		Double[] a = new Double[N];
		for (int i = 0 ; i < N ; i++) {
			a[i] = StdRandom.uniform();
		}
		//1. 数组有序
		Arrays.sort(a);
		return a;
	}
	
	//2. 逆序数组
	private static Double[] reverseArr(int N) {
		Double[] a = sortedArr(N);
		Double[] at = new Double[N];
		for (int i = 0 ; i < at.length ; i++) {
			at[N - i - 1] = a[i];
		}
		return at;
	}
	
	// 只有两种元素的数组
	private static Double[] twoEleArr(int N) {
		Double[] a = new Double[N];
		for (int i = 0 ; i < N ; i++) {
			if (i % 2 == 0) {
				a[i] = 0d;
			} else {
				a[i] = 1d;
			}
		}
		return a;
	}
	
	// 只有一种元素的数组
	private static Double[] oneEleArr(int N) {
		Double[] a = new Double[N];
		for (int i = 0 ; i < N ; i++) {
			a[i] = 1d;
		}
		return a;
	}
	
	// 一半是0，四分之一是1，其余是2
	private static Double[] zeroOneAndTwo(int N) {
		Double[] a = new Double[N];
		int i = 0;
		for (i = 0 ; i < N / 2 ; i++) {
			a[i] = 0d;
		}
		int end1 = N * 3 / 4;
		for (int n = i ; n < end1 ; n++, i++) {
			a[n] = 1d;
		}
		for (int n = i ; n < N ; n++) {
			a[n] = 2d;
		}
		shuffle(a);
		return a;
	}
	
	// 一半是0，其余是随机值
	private static Double[] zeroAndRandom(int N) {
		Double[] a = new Double[N];
		int i = 0;
		for (i = 0 ; i < N / 2 ; i++) {
			a[i] = 0d;
		}
		for (int n = i ; n < N ; n++) {
			a[n] = StdRandom.uniform(0, Double.MAX_VALUE);
		}
		shuffle(a);
		return a;
	}
	
	private static void shuffle(Comparable[] a) {
		List<Comparable> list = new ArrayList<>(a.length);
		for (int i = 0 ; i < a.length ; i++) {
			list.add(a[i]);
		}
		Collections.shuffle(list);
		for (int i = 0 ; i < a.length ; i++) {
			a[i] = list.get(i);
		}
	}
	
	public static void main(String[] args) {
		int N = Double.valueOf(Math.pow(2, 15)).intValue();
		int T = 1;
		System.out.println("插入排序");
		System.out.println(timeRandomInput("sort", "Insertion", N, T));
		System.out.println(timeRandomInput("reverse", "Insertion", N, T));
		System.out.println(timeRandomInput("one", "Insertion", N, T));
		System.out.println(timeRandomInput("two", "Insertion", N, T));
		System.out.println(timeRandomInput("zeroOneAndTwo", "Inertion",N, T));
		System.out.println(timeRandomInput("zeroAndRandom", "Inertion",N, T));
		System.out.println("选择排序");
		System.out.println(timeRandomInput("sort", "Selection", N, T));
		System.out.println(timeRandomInput("reverse", "Selection", N, T));
		System.out.println(timeRandomInput("one", "Selection", N, T));
		System.out.println(timeRandomInput("two", "Selection", N, T));
		System.out.println(timeRandomInput("zeroOneAndTwo", "Selection",N, T));
		System.out.println(timeRandomInput("zeroAndRandom", "Selection",N, T));
		System.out.println("希尔排序");
		System.out.println(timeRandomInput("sort", "Shell", N, T));
		System.out.println(timeRandomInput("reverse", "Shell", N, T));
		System.out.println(timeRandomInput("one", "Shell", N, T));
		System.out.println(timeRandomInput("two", "Shell", N, T));
		System.out.println(timeRandomInput("zeroOneAndTwo", "Shell",N, T));
		System.out.println(timeRandomInput("zeroAndRandom", "Shell",N, T));
	}
}
