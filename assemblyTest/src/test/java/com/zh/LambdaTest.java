package com.zh;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        List<String> strings =
                Arrays.stream(new Integer[]{1,2,3,4})
                    .map(x -> String.valueOf(x))
                    .collect(Collectors.toList());
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

    @Test
    public void testGroupBy() {
        List<MyData> datas = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++) {
            datas.add(new MyData("a", new BigDecimal(i)));
        }
        for (int i = 11 ; i < 20 ; i++) {
            datas.add(new MyData("b", new BigDecimal(i)));
        }
        long start = System.currentTimeMillis();
        for (int i = 0 ; i < 1000000 ; i++) {
            Map<String, BigDecimal> o = datas.stream().collect(
                    Collectors.groupingBy(
                            MyData::getKey,
                            Collectors.reducing(BigDecimal.ZERO, MyData::getValue, BigDecimal::add)
                    )
            );
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static class MyData {

        private String key;

        private BigDecimal value;

        public MyData(String key, BigDecimal value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }
    }

    /**
     * 将两个数组 排列组合
     */
    @Test
    public void testFlatMap() {
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(2, 3);
        List<List<Integer>> list = a.stream()
                .map(i -> b.stream().map(j -> Arrays.asList(i, j)))
                .flatMap(listStream -> listStream)
                .collect(Collectors.toList());
        System.out.println(list);

        list = a.stream()
                .flatMap(i -> b.stream().map(j -> Arrays.asList(i, j)))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void testJoining() {
        System.out.println(IntStream.of(1, 2, 3, 4)
                .boxed()
                .map(i -> String.valueOf(i))
                .collect(Collectors.joining(", ")));
    }
}