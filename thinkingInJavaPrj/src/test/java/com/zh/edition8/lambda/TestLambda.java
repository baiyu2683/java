package com.zh.edition8.lambda;

import org.junit.Test;

/**
 * Created by zhangheng on 2017/1/11.
 */
public class TestLambda {

    @Test
    public void test1() {
        TestLambda testLambda = new TestLambda();
        MathOperation addition = (int a, int b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (int a, int b) -> { return a * b; };
        MathOperation division = (int a, int b) -> a/b;

        System.out.println("10+5=" + testLambda.operate(10, 5, addition));
        System.out.println("10-5=" + testLambda.operate(10, 5, subtraction));
        System.out.println("10x5=" + testLambda.operate(10, 5, multiplication));
        System.out.println("10/5=" + testLambda.operate(10, 5, division));

        GreetingService greetingService1 = message -> System.out.println("Hello " + message);
        GreetingService greetingService2 = (message) -> System.out.println("Hello " + message);

        greetingService1.sayMessage("Mahesh");
        greetingService2.sayMessage("Suresh");
    }

    @Test
    public void test2() {
        GreetingService greetingService = message -> {
            System.out.println("Hello " + message);
        };
    }

    interface MathOperation{
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}
