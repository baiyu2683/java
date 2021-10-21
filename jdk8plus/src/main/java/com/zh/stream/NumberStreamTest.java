package com.zh.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 普通流没有sum、max、min等专属操作
 * 可以通过转换方法进行数值转换后再调用
 *  就是将普通对象流转成IntStream、LongStream、DoubleStream
 */
public class NumberStreamTest {

    @Test
    public void sum() {
        List<Integer> n = Arrays.asList(1, 2, 3, 4);
        IntStream intStream = n.stream().mapToInt(Integer::intValue);
        System.out.println(intStream.sum());
        System.out.println(intStream.max());
        System.out.println(intStream.min());
        // 重新转换会对象
        Stream<Integer> stream = intStream.boxed();
    }

    @Test
    public void optionInt() {
        List<Integer> n = Arrays.asList(1, 2, 3, 4);
        OptionalInt max = n.stream()
                .mapToInt(Integer::intValue)
                .max();
        System.out.println(max.orElse(1));
    }

    @Test
    public void rangeInt() {
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
//        System.out.println(evenNumbers.count());
        System.out.println(evenNumbers.max().orElse(1));
    }

    @Test
    public void gougu() {
        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b ->
                                        new int[] {a, b, (int)Math.sqrt(a * a + b * b)})
                ).forEach(a -> System.out.println(Arrays.toString(a)));
    }
}
