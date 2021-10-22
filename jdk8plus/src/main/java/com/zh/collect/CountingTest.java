package com.zh.collect;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountingTest {


    @Test
    public void counting() {
        List<Integer> n = Arrays.asList(1, 3, 5);
        long count = n.stream().collect(Collectors.counting());
        System.out.println(count);
    }

    @Test
    public void count() {
        List<Integer> n = Arrays.asList(1, 2, 5);
        System.out.println(n.stream().count());
    }
}
