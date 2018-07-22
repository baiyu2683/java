package utils;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author zh2683
 */
public class IntGenerator {

    private static Random random;

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

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generatorIntArr(200)));
    }
}
