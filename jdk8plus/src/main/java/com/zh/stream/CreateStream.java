package com.zh.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class CreateStream {

    @Test
    public void of() {
        Stream<String> stream = Stream.of("Modern ", "Java", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    @Test
    public void empty() {
        Stream<String> emptyStream = Stream.empty();
        System.out.println(emptyStream.count());
    }

    @Test
    public void fromNullable() {
        // 如果是null的话不加入
        Stream<String> value = Stream.ofNullable(System.getProperty("home"));
        Stream<String> value1 =
                Stream.of("config", "home", "user", null);
        value.forEach(System.out::println);
        value1.forEach(System.out::println);
        System.out.println("------");
        // 配合flatMap
        Stream<String> values =
                Stream.of("config", "home", "user")
                        .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
        values.forEach(System.out::println);
    }

    @Test
    public void fromArray() {
        int[] numbers = {2, 3, 4, 5, 6};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
    }

    @Test
    public void fromFile() {
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("FilterTest.class"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println(uniqueWords);
        } catch (IOException e) {

        }
    }

    /**
     * 迭代，无界流
     */
    @Test
    public void fromIterate() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * 生成， 无界流
     */
    @Test
    public void fromGenerate() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
