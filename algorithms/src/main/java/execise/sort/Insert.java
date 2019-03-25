package execise.sort;

import utils.IntGenerator;

import java.util.Arrays;

/**
 * 插入排序
 * 时间复杂度O(n^2) 空间复杂度O(1)
 * 如果恰好是已经排过序的，只需要比较n-1次，交换0次
 */
public class Insert {

    public static void main(String[] args) {
        int[] data = IntGenerator.generatorRandomIntArr(100);
        System.out.println(Arrays.toString(data));
        sort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] data) {
        for (int i = 1 ; i < data.length ; i++) {
            for (int j = i ; j > 0 ; j--) {
                if (data[j] < data[j - 1]) {
                    int tmp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = tmp;
                } else {
                    break;
                }
            }
        }
    }
}
