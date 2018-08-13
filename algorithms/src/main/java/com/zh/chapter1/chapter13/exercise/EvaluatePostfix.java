package com.zh.chapter1.chapter13.exercise;

import com.zh.chapter1.chapter13.Stack;

import utils.MathUtil;

/**
 * 接收一个后序表达式，计算出结果
 * @author zh
 * 2018年8月13日
 */
public class EvaluatePostfix {

	public static void main(String[] args) {
		String expression = "1 2 + 3 4 - 5 6 - * *";
		String[] exps = expression.split(" ");
		Stack<String> resultStack = new Stack<>();
		for (int i = 0 ; i < exps.length ; i++) {
			String exp = exps[i];
			if (MathUtil.isOperator(exp)) {
				int d1 = Integer.parseInt(resultStack.pop());
				int d2 = Integer.parseInt(resultStack.pop());
				if (MathUtil.isPlus(exp)) {
					resultStack.push(String.valueOf(d2 + d1));
				} else if (MathUtil.isMinus(exp)) {
					resultStack.push(String.valueOf(d2 - d1));
				} else if (MathUtil.isMultiplication(exp)) {
					resultStack.push(String.valueOf(d2 * d1));
				} else if (MathUtil.isDivision(String.valueOf(d2 / d1)));
			} else {
				resultStack.push(exp);
			}
		}
		System.out.println(resultStack.pop());
	}
}
