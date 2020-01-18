package com.zh.chapter1.chapter13;

import lombok.Data;
import lombok.NonNull;

import java.util.*;

/**
 * 双栈法计算表达式的值
 * @Author zh2683
 */
public class Evaluate {

    public static void main(String[] args) {
        String expression = "($1$ and not $2,3,4$) or not $2,3$";
        Value result = evaluateExpress(expression);
        System.out.println(result.toString());
    }

    private static Value evaluateExpress(String expression) {
        String[] subStr = expression.split("\\s*");
        return evaluateExpress(subStr);
    }

    private static Value evaluateExpress(String[] expression) {
        Deque<String> ops = new LinkedList<>();
        Deque<Value> vals = new LinkedList<>();
        // 单个数字
        StringBuilder data = new StringBuilder();
        // 一个$$中间所有的数字
        List<Integer> dataList = new ArrayList<>();
        boolean isValue = false;
        boolean isNot = false;
        for (int i = 0 ; i < expression.length ; i++) {
            String sub = expression[i];
            if (sub.equals("(") && isNot) {
                //  优先计算not(expression)格式的子表达式
                Deque<String> subOps = new LinkedList<>();
                subOps.push("(");
                List<String> notExpress = new ArrayList<>();
                int j = i + 1;
                for (; j < expression.length ; j++) {
                    String content = expression[j];
                    if (content.equals(")")) {
                        subOps.pop();
                    } else {
                        notExpress.add(content);
                    }
                    if (subOps.isEmpty()) {
                        break;
                    }
                }
                Value value = evaluateExpress(notExpress.toArray(new String[0]));
                if (!value.isNot()) {
                    value.setNot(true);
                }
                isNot = false;
                vals.push(value);
                i = j + 1;
            } else if (sub.equals("$")) {
                if (isValue) {

                    //最后一个数字
                    if (data.length() > 0) {
                        dataList.add(Integer.valueOf(data.toString()));
                        data.setLength(0);
                    }

                    // 第二个$
                    List<Integer> clone = new ArrayList<>();
                    for (Integer d : dataList) {
                        clone.add(d);
                    }
                    Value value = new Value(clone);
                    if (isNot) {
                        // not $1,2,3$ 格式
                        value.setNot(isNot);
                        isNot = false;
                    }

                    vals.push(value);
                    dataList.clear();
                }
                isValue = !isValue;
            } else if (sub.equals(",") || isValue) {
                if (!sub.equals(",")) {
                    data.append(sub);
                } else {
                    if (data.length() > 0) {
                        dataList.add(Integer.valueOf(data.toString()));
                        data.setLength(0);
                    }
                }
            } else if (sub.equals("a")) {
                // 操作符入操作符栈
                ops.push("and");
                i += 2;
            } else if (sub.equals("o")) {
                ops.push("or");
                i += 1;
            } else if (sub.equals("n")) {
//                ops.push("not");
                i += 2;
                isNot = true;
            } else if (sub.equals(")")) {
                evaluate(ops, vals);
            }
        }
        while (ops.size() > 0) {
            evaluate(ops, vals);
        }
        return vals.pop();
    }

    private static void evaluate(Deque<String> ops, Deque<Value> vals) {
        // 操作符出栈
        String op = ops.pop();
        Value result = null;
        // 第一个操作数出栈
        Value firstValue = vals.pop();
        if (op.equals("and")) {

            Set<Integer> first = new HashSet<>(firstValue.getValues());
            boolean firstNot = firstValue.isNot();

            Value secondValue = vals.pop();
            Set<Integer> second = new HashSet<>(secondValue.getValues());
            boolean secondNot = secondValue.isNot();

            if (firstNot && !secondNot) {
                second.remove(first);
                result = new Value(new ArrayList<>(second));
            } else if (!firstNot && secondNot) {
                first.remove(second);
                result = new Value(new ArrayList<>(first));
            } else if (firstNot && secondNot) {
                first.addAll(second);
                result = new Value(new ArrayList<>(first));
                result.setNot(true);
            } else {
                first.retainAll(second);
                result = new Value(new ArrayList<>(first));
            }
        } else if (op.equals("or")) {
            Set<Integer> first = new HashSet<>(firstValue.getValues());
            boolean firstNot = firstValue.isNot();

            Value secondValue = vals.pop();
            Set<Integer> second = new HashSet<>(secondValue.getValues());
            boolean secondNot = secondValue.isNot();

            if (firstNot && !secondNot) {
                first.remove(second);
                result = new Value(new ArrayList<>(first));
                result.setNot(true);
            } else if (!firstNot && secondNot) {
                second.remove(first);
                result = new Value(new ArrayList<>(second));
                result.setNot(true);
            } else if (firstNot && secondNot) {
                first.retainAll(second);
                result = new Value(new ArrayList<>(first));
                result.setNot(true);
            } else {
                first.addAll(second);
                result = new Value(new ArrayList<>(first));
            }
        }
        // 值压栈
        vals.push(result);
    }

    @Data
    private static class Value {
        //是否包含values
        private boolean not;
        @NonNull
        private List<Integer> values;
    }

}
