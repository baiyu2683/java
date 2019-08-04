package com.zh;

/**
 * 输入一个数组打印图形
 * 1
 * *
 * 2
 * _*_
 * *_*
 * _*_
 *
 * 3
 * __*__
 * _*_*_
 * *_*_*
 * _*_*_
 * __*__
 */
public class PrintTest {

    public void print(int x) {
        int count = 2 * x - 1;
        String[] results = new String[x];
        for (int i = 1 ; i <= x ; i++) {
            results[i - 1] = convert(i, count);
        }

        // 打印
        for (int i = 0 ; i < results.length ; i++) {
            System.out.println(results[i]);
        }
        for (int i = results.length - 2 ; i >= 0 ; i--) {
            System.out.println(results[i]);
        }
    }

    /**
     * 根据行号和每行的长度构造字符串
     * @param n  >= 1
     * @return
     */
    private String convert(int n, int length) {
        // 1. 构造*和_间隔的串
        String result = "";
        while (n-- > 0) {
            result += "*_";
        }
        String temp = result.substring(0, result.length() - 1);
        // 2. 补两边缺失的_
        if (temp.length() < length) {
            int underlineHalfCount = (length - temp.length()) / 2;
            String undelineStr = "";
            while (underlineHalfCount-- > 0) {
                undelineStr += "_";
            }
            temp  = undelineStr + temp + undelineStr;
        }
        return temp;
    }

    public static void main(String[] args) {
        PrintTest printTest = new PrintTest();
        printTest.print(10);
    }
}
