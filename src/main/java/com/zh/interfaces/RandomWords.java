package com.zh.interfaces;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by zh on 2017-04-13.
 */
public class RandomWords implements Readable {
    private static Random random = new Random(47);
    private static final char[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] lowers = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] vowels = "aeiou".toCharArray();
    private int count;
    public RandomWords(int count) {
        this.count = count;
    }
    public int read(CharBuffer charBuffer) {
        if(count-- == 0) return -1;
        charBuffer.append(capitals[random.nextInt(capitals.length)]);
        for (int i = 0; i < 4; i++) {
            charBuffer.append(vowels[random.nextInt(vowels.length)]);
            charBuffer.append(lowers[random.nextInt(lowers.length)]);
        }
        charBuffer.append(" ");
        return 10;//Number of characters appended
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new RandomWords(10));
        while (scanner.hasNext())
            System.out.println(scanner.next());
    }
}
