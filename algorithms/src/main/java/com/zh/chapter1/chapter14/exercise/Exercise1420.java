package com.zh.chapter1.chapter14.exercise;

import java.util.Arrays;
import java.util.Random;

/**
 * 双调查找，
 * 如果一个数组所有元素都是先递增后递减的，则这个数组称为双调的
 *
 * 给定一个含有N个不同int值的双调数组，判断他是否含有给定的整数
 */
public class Exercise1420 {

    public static boolean findValue(int value, int[] arr) {
        int left = arr[0];
        int right = arr[arr.length - 1];
        int midIndex = -1;
        int prefix = left;
        for (int i = 1 ; i < arr.length ; i++) {
            if (prefix > arr[i]) {
                midIndex = i - 1;
                break;
            }
            prefix = arr[i];
        }
        // value比最大值还大
        if (arr[midIndex] < value) return false;
        else {
            int leftArr = Arrays.binarySearch(arr, left, midIndex + 1, value);
            if (leftArr == -1) {
                int rightArrIndex = Arrays.binarySearch(arr, midIndex, arr.length, value);
                if (rightArrIndex == -1) return false;
                else return true;
            } else return true;
        }
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        int[] arr = new int[100];
        for (int i = 0 ; i < 100 ; i++) {
            if (i < 43) {
                arr[i] = i;
            } else {
                arr[i] = 100 - i;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(findValue(57, arr));
        System.out.println(findValue(58, arr));
    }
}
