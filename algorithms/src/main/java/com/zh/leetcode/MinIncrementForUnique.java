package com.zh.leetcode;

import javax.swing.plaf.IconUIResource;
import java.util.Arrays;

/**
 * //给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 *
 * // 返回使 A 中的每个值都是唯一的最少操作次数。
 *
 * // 示例 1:
 *
 * // 输入：[1,2,2]
 * // 输出：1
 * // 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 * // 示例 2:
 *
 * // 输入：[3,2,1,2,1,7]
 * // 输出：6
 * // 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * // 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 * // 提示：
 *
 * // 0 <= A.length <= 40000
 * // 0 <= A[i] < 40000
 *
 * // 来源：力扣（LeetCode）
 * // 链接：https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique
 * // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinIncrementForUnique {

    public static void main(String[] args) {
        int[] a = new int[] {3,2,1,2,1,7};
        MinIncrementForUnique minIncrementForUnique = new MinIncrementForUnique();
        System.out.println(minIncrementForUnique.minIncrementForUnique3(a));
    }


    public int minIncrementForUnique3(int[] A) {
        if (A.length <= 1) {
            return 0;
        }
        // counter sort
        int[] temp = new int[80000];
        for (int i = 0 ; i < A.length ; i++) {
            temp[A[i]]++;
        }
        int count = 0;
        for (int i = 0 ; i < temp.length ; i++) {
            if (temp[i] > 1) {
                int tempCount = temp[i] - 1;
                temp[i+1] += tempCount;
                temp[i] = 1;
                count += tempCount;
            }
        }
        return count;
    }


    public int minIncrementForUnique2(int[] A) {
        Arrays.sort(A);
        int pre = -1;
        int move = 0;
        for (int i = 0 ; i < A.length ; i++) {
            if (A[i] <= pre) {
                move += pre - A[i] + 1;
                A[i] = pre + 1;
            }
            pre = A[i];
        }
        return move;
    }

    /**
     *
     * @param A
     * @return
     */
    public int minIncrementForUnique(int[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        sort(A, 0, A.length - 1);
        int pre = A[0];
        int move = 0;
        for (int i = 1 ; i < A.length ; i++) {
            if (A[i] <= pre) {
                move += pre - A[i] + 1;
                A[i] = pre + 1;
            }
            pre = A[i];
        }
        return move;
    }


    private void sort(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = threeMedian(a, low, high);
        int l = low;
        int h = high - 1;
        // only three elements
        if (l == h) {
            return;
        }
        while (true) {
            while (a[++l] < pivot) {

            }
            while (a[--h] > pivot) {

            }
            if (l >= h) {
                break;
            }
            exchange(a, l, h);
        }
        exchange(a, l, high - 1);

        sort(a, low, l - 1);
        sort(a, l + 1, high);
    }

    //
    private int threeMedian(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (a[low] > a[mid]) {
            exchange(a, low, mid);
        }
        if (a[mid] > a[high]) {
            exchange(a, mid, high);
        }
        if (a[low] > a[mid]) {
            exchange(a, low, mid);
        }
        exchange(a, mid, high - 1);
        return a[high - 1];
    }

    private void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
