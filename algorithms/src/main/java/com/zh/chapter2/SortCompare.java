package com.zh.chapter2;

import com.zh.chapter1.chapter14.StopWatch;
import com.zh.chapter2.chapter21.Insertion;
import com.zh.chapter2.chapter21.InsertionAdvance;
import com.zh.chapter2.chapter21.Selection;
import com.zh.chapter2.chapter21.Shell;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 比较排序算法
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
			total += time(alg, a);
		}
		return total;
	}
	
	// 插入排序比选择排序快一倍
	public static void main(String[] args) {
		int N = Double.valueOf(Math.pow(2, 15)).intValue();
		int T = 1;
		double t1 = SortCompare.timeRandomInput("Insertion", N, T);
		System.out.println("insertion: " + t1);
		double t4 = SortCompare.timeRandomInput("InsertionAdvance", N, T);
		System.out.println("InsertionAdvance: " + t4);
//		double t2 = SortCompare.timeRandomInput("Selection", N, T);
//		System.out.println("Selection: " + t2);
//		double t3 = SortCompare.timeRandomInput("Shell", N, T);
//		System.out.println("Shell: " + t3);
	}
}
