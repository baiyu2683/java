package com.zh.collect;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaxMinTest {

    @Test
    public void max() {
        List<Integer> n = Arrays.asList(1, 2, 4, 6);
        Comparator<Integer> maxComparator = Comparator.comparingInt(Integer::intValue);
        Optional<Integer> max = n.stream().collect(Collectors.maxBy(maxComparator));
        System.out.println(max);
        System.out.println(n.stream().collect(Collectors.minBy(maxComparator)));
    }
}
