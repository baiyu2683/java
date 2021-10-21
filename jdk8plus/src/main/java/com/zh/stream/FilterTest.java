package com.zh.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterTest {

    /**
     * filter会遍历表里所有的元素并筛选出满足条件的元素
     */
    @Test
    public void filter() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

    }

    /**
     * takeWhile
     * 当集合已经被排过序，可以使用takeWhile对集合进行切片
     * 不会像filter一样遍历整个集合，而是满足条件是就终止返回
     */
    @Test
    public void takeWhile() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> n2 = numbers.stream()
                .takeWhile(i -> i < 4)
                .collect(Collectors.toList());
        System.out.println(n2);
    }

    /**
     * takeWhile取从头开始满足条件的元素
     * dropWhile取剩余的元素
     */
    @Test
    public void dropWhile() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> n2 = numbers.stream()
                .dropWhile(i -> i < 4)
                .collect(Collectors.toList());
        System.out.println(n2);
    }

    /**
     * 截短流,取指定个数的元素
     */
    @Test
    public void limit() {
        List<Integer> n = Arrays.asList(1, 2, 5, 3);
        List<Integer> n1 = n.stream()
                .filter(i -> i < 4)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(n1);
    }

    /**
     * 和limit相对，跳过指定个数的元素
     */
    @Test
    public void skip() {
        List<Integer> n = Arrays.asList(1, 2, 5, 3);
        List<Integer> n1 = n.stream()
                .filter(i -> i < 4)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(n1);
    }
}
