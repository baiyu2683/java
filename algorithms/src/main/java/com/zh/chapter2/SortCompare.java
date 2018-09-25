package com.zh.chapter2;

import com.zh.chapter1.chapter14.StopWatch;
import com.zh.chapter2.chapter21.Insertion;
import com.zh.chapter2.chapter21.InsertionAdvance;
import com.zh.chapter2.chapter21.InsertionInt;
import com.zh.chapter2.chapter21.InsertionSentry;
import com.zh.chapter2.chapter21.Selection;
import com.zh.chapter2.chapter21.Shell;
import com.zh.chapter2.chapter21.example.Exercise2134;
import com.zh.chapter2.chapter22.Merge;
import com.zh.chapter2.chapter23.Quick;

import com.zh.chapter2.chapter23.QuickAdvanced;
import com.zh.chapter2.chapter23.QuickAdvancedThreeMedian;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 排序算法比较
 * @author zh
 * 2018年9月12日
 */
public class SortCompare {

	public static double time(String alg, Double[] a) {
		StopWatch sw = new StopWatch();
		if (alg.equals("Insertion")) Insertion.sort(a);
		if (alg.equals("InsertionAdvance")) InsertionAdvance.sort(a);
		if (alg.equals("Selection")) Selection.sort(a);
		if (alg.equals("Shell")) Shell.sort(a);
		if (alg.equals("Merge")) Merge.sort(a);
		if (alg.equals("Quick")) Quick.sort(a);
		if (alg.equals("QuickAdvanced")) QuickAdvanced.sort(a);
		if (alg.equals("QuickAdvancedThreeMedian")) QuickAdvancedThreeMedian.sort(a);
		// ...
		return sw.elapseMillTime();
	}
	
	public static double timeRandomInput(String alg, int N, int T) {
		// 使用算法alg 将T个长度为N的输入排序
		double total = 0.0;
		Double[] a = new Double[N];
		for (int t = 0 ; t < T ; t++) {
			// 一次测试
			for (int i = 0 ; i < N ; i++) {
				a[i] = StdRandom.uniform();
			}
			// 打乱数组
			List<Comparable> list = new ArrayList<>();
			list.addAll(Arrays.asList(a));
			Collections.shuffle(list);
			list.toArray(a);

			total += time(alg, a);
		}
		return total;
	}
	
	public static double timeInt(String alg, int[] a) {
		StopWatch sw = new StopWatch();
		if (alg.equals("InsertionInt")) InsertionInt.sort(a);
		// ...
		return sw.elapseMillTime();
	}
	// 原始数据类型int
	public static double timeRandomIntInput(String alg, int N, int T) {
		// 使用算法alg 将T个长度为N的输入排序
		double total = 0.0;
		int[] a = new int[N];
		for (int t = 0 ; t < T ; t++) {
			// 一次测试
			for (int i = 0 ; i < N ; i++) {
				a[i] = StdRandom.uniform(1, Integer.MAX_VALUE);
			}
			total += timeInt(alg, a);
		}
		return total;
	}
	
	public static void main(String[] args) {
		int N = Double.valueOf(Math.pow(2, 20)).intValue();
		int T = 1;
//		double t1 = SortCompare.timeRandomInput("Insertion", N, T);
//		System.out.println("insertion: " + t1);
////		double t5 = SortCompare.timeRandomIntInput("InsertionInt", N, T);
////		System.out.println("InsertionInt: " + t5);
//		double t4 = SortCompare.timeRandomInput("InsertionAdvance", N, T);
//		System.out.println("InsertionAdvance: " + t4);
//		double t2 = SortCompare.timeRandomInput("Selection", N, T);
//		System.out.println("Selection: " + t2);
//		double t3 = SortCompare.timeRandomInput("Shell", N, T);
//		System.out.println("Shell: " + t3);
		double t7 = SortCompare.timeRandomInput("Quick", N, T);
		System.out.println("Quick: " + t7);
		double t8 = SortCompare.timeRandomInput("QuickAdvanced", N, T);
		System.out.println("QuickAdvanced: " + t8);
		double t9 = SortCompare.timeRandomInput("QuickAdvancedThreeMedian", N, T);
		System.out.println("QuickAdvancedThreeMedian: " + t9);
//		double t6 = SortCompare.timeRandomInput("Merge", N, T);
//		System.out.println("Merge: " + t6);
	}
}
