package com.zh.chapter1.chapter13.exercise;

import com.zh.chapter1.chapter13.Stack;

/**
 * 补全缺失的左括号
 * 双栈法
 * @author zh
 * 2018年8月13日
 */
public class Exercise139 {

	public static void main(String[] args) {
		String expression = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
		Stack<String> nums = new Stack<>();
		Stack<String> operators = new Stack<>();
		String[] exps = expression.split(" ");
		for (String exp : exps) {
			if ("+".equals(exp)) operators.push(exp);
			else if ("-".equals(exp)) operators.push(exp);
			else if ("*".equals(exp)) operators.push(exp);
			else if ("/".equals(exp)) operators.push(exp);
			else if (")".equals(exp)){
				String o = operators.pop();
				String n1 = nums.pop();
				String n2 = nums.pop();
				// 将结果拼接好放入nums栈中
				String result = "(" + n2 + o + n1 + ")";
				nums.push(result);
			} else {
				nums.push(exp);
			}
		}
		for (String s : nums) {
			System.out.println(s);
		}
	}
}
