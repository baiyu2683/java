package com.zh;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @date 2019/06/06
 */
public class LambdaTest {

    @Test
    public void reduce() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        Integer result = list.stream().reduce((before, after) -> before + after)
                .get();
        System.out.println(result);
    }

    @Test
    public void max() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        Integer result = list.stream().max((x, y) -> x > y ? 1 : -1).get();
        System.out.println(result);
    }

    @Test
    public void summary() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10000000; i++) {
            list.add(i);
        }
        LongSummaryStatistics longSummaryStatistics = list.stream()
                .mapToLong(i -> Long.valueOf(i))
                .summaryStatistics();
        System.out.println(longSummaryStatistics.getMax());
        System.out.println(longSummaryStatistics.getMin());
        System.out.println(longSummaryStatistics.getCount());
        System.out.println(longSummaryStatistics.getAverage());
    }

    @Test
    public void objectSummary() {
        List<Group> list = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            if (i <= 50) {
                list.add(new Group(String.valueOf(i), i));
            } else {
                list.add(new Group("10", i));
            }
        }
        Map<String, List<Group>> result = list.stream()
                .collect(Collectors.groupingBy(Group::getX));
        System.out.println(result);
    }

    @Data
    @AllArgsConstructor
    static class Group {
        private String x;
        private Integer y;
    }

    /**
     * 降维
     */
    @Test
    public void flatMap() {
        List<Object[]> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add(new Object[]{i, i + 1});
        }
        List<Object> result = list.stream()
                .flatMap(objects -> Arrays.stream(objects))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
    }

}