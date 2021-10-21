package com.zh.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 流的映射
 * map flatMap
 */
public class MapTest {

    @Test
    public void map() {
        List<String> s = Arrays.asList("123", "1", "", "99999");
        List<Integer> s1 = s.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(s1);
    }

    /**
     * flatMap用于降维，例如二维数组的流组合成一个一维数组的流
     */
    @Test
    public void flatMap() {
        List<String> s = Arrays.asList("Hello", "World");
        List<String> s1 = s.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(s1);
    }

    /**
     * 通过两个数组获得所有数对
     * [1,2]和[3,4]
     * 结果: [1,3] [1,4] [2,3] [2,4]
     */
    @Test
    public void exercise() {
        List<Integer> n1 = Arrays.asList(1, 2, 3);
        List<Integer> n2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                n1.stream()
                        .flatMap(i -> n2.stream().map(j -> new int[]{i, j}))
                        .collect(Collectors.toList());
        pairs.stream()
                .forEach(p -> System.out.println(Arrays.toString(p)));
    }
}
