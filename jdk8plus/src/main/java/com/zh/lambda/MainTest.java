package com.zh.lambda;

import com.zh.entity.Apple;
import com.zh.enums.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class MainTest {

    public static void testLambdaGrammar() {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(Color.RED));
        apples.add(new Apple(Color.GREEN));
        apples.stream().forEach(apple -> {
            System.out.println(apple.getColor());
            throw new RuntimeException("lambda中的异常");
        });
    }

    public static void main(String[] args) {
        MainTest.testLambdaGrammar();

        Comparator<Apple> comparator = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };
        Comparator<Apple> comparator1 = (Apple a1, Apple a2) -> a1.getColor().compareTo(a2.getColor());
    }
}
