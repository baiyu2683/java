package com.zh.chapter1.chapter13;

import java.util.*;
import java.util.Stack;

/**
 * 双栈法计算表达式的值
 * @Author zh2683
 */
public class Evaluate {

    public static void main(String[] args) {
        String expression = "($1004$ and $1001,1002,1003,1004$)";
        List<Integer> list = evaluateExpress(expression);
        System.out.println(Arrays.toString(list.toArray()));
    }

    private static List<Integer> evaluateExpress(String expression) {
        String[] subStr = expression.split("\\s*");

        Stack<String> ops = new Stack<>();
        Stack<List<Integer>> vals = new Stack<>();
        // 单个数字
        StringBuilder data = new StringBuilder();
        // 一个$$中间所有的数字
        List<Integer> dataList = new ArrayList<>();
        boolean isValue = false;
        for (int i = 0 ; i < subStr.length ; i++) {
            String sub = subStr[i];
            // 忽略左括号
            if (sub.equals("(")) {
            } else if (sub.equals("$")) {
                if (isValue) {
                    //最后一个数字
                    dataList.add(Integer.valueOf(data.toString()));
                    data.setLength(0);
                    // 第二个$
                    List<Integer> clone = new ArrayList<>();
                    for (Integer d : dataList) {
                        clone.add(d);
                    }
                    vals.push(clone);
                    dataList.clear();
                }
                isValue = !isValue;
            } else if (sub.equals(",") || isValue) {
                if (!sub.equals(",")) {
                    data.append(sub);
                } else {
                    dataList.add(Integer.valueOf(data.toString()));
                    data.setLength(0);
                }
            } else if (sub.equals("a")) {
                // 操作符入操作符栈
                ops.push("and");
                i += 2;
            } else if (sub.equals("o")) {
                ops.push("or");
                i += 1;
            } else if (sub.equals(")")) {
                // 操作符出栈
                String op = ops.pop();
                // 第一个操作数出栈
                List<Integer> v = vals.pop();
                Set<Integer> set = new HashSet<>(v);
                if (op.equals("and")) {
                    // 求交集
                    set.retainAll(vals.pop());
                    v = new ArrayList<>(set);
                }else if (op.equals("or")) {
                    // 求并集
                    set.addAll(vals.pop());
                    v = new ArrayList<>(set);
                }
                // 值压栈
                vals.push(v);
            }
        }
        return vals.pop();
    }
}
