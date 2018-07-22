package com.zh.chapter1.exercise;

import java.util.Scanner;

/**
 * @Author zh2683
 */
public class Exercise113 {
    public static void main(String[] args) {
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        int[] in = new int[3];
        while (scanner.hasNext()) {
            in[i] = Integer.parseInt(scanner.next());
            if (++i >= 3) break;
        }
        if (in[0] == in[1] && in[1] == in[2]) System.out.println("equal");
        else System.out.println("not equal");
    }
}
