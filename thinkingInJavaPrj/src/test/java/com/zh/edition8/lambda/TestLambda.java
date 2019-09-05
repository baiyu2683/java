package com.zh.edition8.lambda;

import lombok.ToString;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

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

    @Test
    public void testFilter() {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setName("123");
        list.add(user);
        User user1 = new User();
        user1.setName("asd");
        list.add(user1);
        User s = list.stream().filter(x -> x.getName().equals("123")).findFirst().get();
        System.out.println(s.getName());
    }

    static class User{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
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
    
    @Test
    public void testPartition() {
        List<Partition> ps = new ArrayList<>();
        for (int i = 0 ; i < 100 ; i++) {
            if (i % 2 == 0) {
                ps.add(new Partition(true, String.valueOf(i)));
            } else {
                ps.add(new Partition(false, String.valueOf(i)));
            }
        }
        Map<Boolean, List<Partition>> result = ps.stream().collect(
                Collectors.partitioningBy(Partition::getFlag, Collectors.toList()));
        System.out.println(JSONObject.toJSONString(result));
    }
    
    @AllArgsConstructor
    @Getter
    static class Partition {
        private Boolean flag;
        
        private String data;
    }

    @Test
    public void testComparing() {
        List<Data> list = new ArrayList<>(6);
        list.add(new Data(12, 5, 14));
        list.add(new Data(23, 29, 19));
        list.add(new Data(24, 16, 18));
        list.add(new Data(24, 16, 4));
        list.add(new Data(26, 29, 10));
        list.add(new Data(12, 28, 18));
        list.forEach(data -> {
            System.out.println(data.toString());
        });
//        Collections.sort(list,
//                Comparator.comparing(Data::getNum1)
//                .thenComparing(Data::getNum2)
//                .thenComparing(Comparator.comparing(Data::getNum3).reversed())
//        );
        list = list.stream().sorted(
                    Comparator.comparing(Data::getNum1)
                        .thenComparing(Data::getNum2)
                        .thenComparing(Comparator.comparing(Data::getNum3).reversed())
                )
                .collect(Collectors.toList());
//        TestLambda.Data(num1=12, num2=5, num3=14)
//        TestLambda.Data(num1=12, num2=28, num3=18)
//        TestLambda.Data(num1=23, num2=29, num3=19)
//        TestLambda.Data(num1=24, num2=16, num3=18)
//        TestLambda.Data(num1=24, num2=16, num3=4)
//        TestLambda.Data(num1=26, num2=29, num3=10)
        System.out.println("=");
        list.forEach(data -> {
            System.out.println(data.toString());
        });
    }

    @AllArgsConstructor
    @Getter
    @ToString
    static class Data {
        private int num1;
        private int num2;
        private int num3;
    }
}
