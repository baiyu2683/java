package com.zh.edition8.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

public class LocalDateTest {

    @Test
    public void test1() {
        LocalDate date = LocalDate.of(2018, 10, 10);
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.isLeapYear());
    }

    @Test
    public void test2() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.get(ChronoField.DAY_OF_MONTH));
        System.out.println(localDate.get(ChronoField.MONTH_OF_YEAR));
    }

    @Test
    public void test3() {
        LocalTime localTime = LocalTime.of(10,10,10);
        System.out.println(localTime.format(DateTimeFormatter.ofPattern("HH/mm/ss")));
        System.out.println(localTime.getHour());
        System.out.println(localTime.getSecond());
    }

    @Test
    public void test4() {
        System.out.println(Instant.now().getEpochSecond());
        System.out.println(Instant.now().toEpochMilli());
    }

    @Test
    public void test5() {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.println(localDateTime.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(localDateTime.toInstant(ZoneOffset.of(ZoneOffset.systemDefault().getId())).toEpochMilli());
    }
}
