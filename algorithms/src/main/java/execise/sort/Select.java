package execise.sort;

import utils.IntGenerator;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度 O(n^2) 空间复杂度O(1)
 * n^2此比较，n次交换
 */
public class Select {

    public static void main(String[] args) {
        int[] data = IntGenerator.generatorRandomIntArr(100);
        System.out.println(Arrays.toString(data));
        sort(data);
        System.out.println(Arrays.toString(data));
    }


    private static void sort(int[] data) {
        for (int i = 0 ; i < data.length ; i++) {
            int min = i;
            for (int j = i + 1 ; j < data.length ; j++) {
                if (data[j] < data[min]) {
                    min = j;
                }
            }
            int tmp = data[i];
            data[i] = data[min];
            data[min] = tmp;
        }
    }
}
