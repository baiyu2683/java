package com.zh.lambda;

import com.zh.entity.Apple;
import com.zh.enums.Color;

import java.util.ArrayList;
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
    }
}
