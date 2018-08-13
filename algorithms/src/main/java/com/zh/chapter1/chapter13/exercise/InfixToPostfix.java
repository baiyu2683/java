package com.zh.chapter1.chapter13.exercise;

import com.zh.chapter1.chapter13.Stack;

import utils.MathUtil;

/**
 * 将一个中序表达式转换为一个后续表达式
 * @author zh
 * 2018年8月13日
 */
public class InfixToPostfix {

	public static void main(String[] args) {
		String expression = "((1+2)*((3-4)*(5-6)))";
		String[] exps = expression.split("");
		Stack<String> datasStack = new Stack<>();
		Stack<String> operatorsStack = new Stack<>();
		for (int i = 0 ; i < exps.length ; i++) {
			String exp = exps[i];
			if ("(".equals(exp)) {
			} else if (MathUtil.isOperator(exp)) {
				operatorsStack.push(exp);
			} else if (")".equals(exp)) {
				String operator = operatorsStack.pop();
				String data1 = datasStack.pop();
				String data2 = datasStack.pop();
				String result = data2 + " " + data1 + " " + operator;
				datasStack.push(result);
			} else {
				datasStack.push(exp);
			}
		}
		System.out.println(datasStack.pop());
	}
}
