package utils;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author zh2683
 */
public class IntGenerator {

    private static Random random;

    /**
     * 生成随机数字数组
     * @param size
     * @return
     */
    public static int[] generatorIntArr(int size) {
        random = new Random(System.currentTimeMillis());
        int bound = size * 100;
        int[] result = new int[size];
        for (int i = 0 ; i < size ; i++) {
            result[i] = random.nextInt(bound);
        }
        Arrays.sort(result);
        return result;
    }
    
    public static Integer[] generatorIntegerArr(int size) {
        random = new Random(System.currentTimeMillis());
        int bound = size * 100;
        Integer[] result = new Integer[size];
        for (int i = 0 ; i < size ; i++) {
            result[i] = random.nextInt(bound);
        }
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generatorIntArr(200)));
    }
}
