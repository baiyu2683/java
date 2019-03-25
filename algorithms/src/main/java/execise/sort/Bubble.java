package execise.sort;

import utils.IntGenerator;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class Bubble {

    public static void main(String[] args) {
        int[] data = IntGenerator.generatorRandomIntArr(100);
        System.out.println(Arrays.toString(data));
        sort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] data) {
        for (int i = 0 ; i < data.length ; i++) {
            for (int j = 0 ; j < data.length - 1 - i ; j++) {
                if (data[j] > data[j + 1]) {
                    int tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
    }
}
