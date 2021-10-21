package com.zh.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 查找和匹配
 */
public class MatchFindTest {

    @Test
    public void anyMatch() {
        //是否有一个元素能够匹配给定谓词
        List<Integer> n = Arrays.asList(1, 2, 3);
        boolean r = n.stream()
                .anyMatch(i -> i > 4);
        System.out.println(r);
    }

    @Test
    public void allMatch() {
        // 是否所有元素都匹配给定谓词
        List<Integer> n = Arrays.asList(1, 2, 3);
        boolean r = n.stream()
                .allMatch(i -> i < 4);
        System.out.println(r);
    }

    @Test
    public void noneMatch() {
        // 和allMatch相对，没有任何元素匹配给定谓词
        List<Integer> n = Arrays.asList(1, 2, 3);
        boolean r = n.stream()
                .noneMatch(i -> i > 3);
        System.out.println(r);
    }

    @Test
    public void findAny() {
        // 返回当前流中的任意元素
        List<Integer> n = Arrays.asList(1, 2, 3);
        Optional<Integer> r = n.stream()
                .findAny();
        r.ifPresent(System.out::println);
    }

    @Test
    public void findFirst() {
        // 获取第一个元素
        List<Integer> n = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> f =
                n.stream()
                        .map(i -> i * i)
                        .filter(i -> i % 3 == 0)
                        .findFirst();
        System.out.println(f.orElse(-1));
    }
}
