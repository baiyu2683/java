package com.zh.leetcode;

import java.util.Arrays;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 */
public class LeastNumbers {

    public static void main(String[] args) {
        LeastNumbers leastNumbers = new LeastNumbers();
        int[] arr = new int[]{4,5,1,6,2,7,3,8};
        System.out.println(Arrays.toString(leastNumbers.getLeastNumbers(arr, 4)));
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1, k);
        int[] result = new int[k];
        int arrLength = arr.length;
        for (int i = 0; i < k && i < arrLength; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    private void quickSort(int[] arr, int l, int h, int k) {
        if (l >= h) {
            return;
        }
        int partition = partition(arr, l, h);
        if (partition >= k) {
            quickSort(arr, l, partition - 1, k);
        } else if (partition < k) {
            quickSort(arr, partition + 1, h, k);
        }
    }

    private int partition(int[] arr, int l, int h) {
        int low = l;
        int high = h + 1;
        int v = arr[l];
        while (true) {
            while (arr[++low] <= v) {
                if (low == h) {
                    break;
                }
            }
            while (arr[--high] >= v) {
                if (high == l) {
                    break;
                }
            }
            if (low >= high) {
                break;
            }
            exchange(arr, low, high);
        }
        // high must less than v
        exchange(arr, l, high);
        return high;
    }

    private void exchange(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
}

