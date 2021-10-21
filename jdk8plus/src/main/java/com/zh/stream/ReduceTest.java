package com.zh.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceTest {

    @Test
    public void reduce() {
        List<Integer> n = Arrays.asList(1, 2, 3, 4);
        Integer sum = n.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }

    @Test
    public void reduceNoInit() {
        List<Integer> n = Arrays.asList(1, 2, 3, 4);
        Optional<Integer> sum = n.stream().reduce((a, b) -> a + b);
        System.out.println(sum.orElse(-1));
    }

    @Test
    public void max() {

    }
}
