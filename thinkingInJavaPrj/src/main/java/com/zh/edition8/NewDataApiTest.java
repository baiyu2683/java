package com.zh.edition8;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class NewDataApiTest {

    @Test
    public void testInstant() {
        // 获取秒
        System.out.println(Instant.now().getEpochSecond());
        // 获取时间戳
        System.out.println(Instant.now().toEpochMilli());
        // 获取3秒时时间
        System.out.println(Instant.ofEpochMilli(3));
    }

    /**
     * duration 为秒和纳秒衡量时间长短，不能使用LocalDate
     */
    @Test
    public void testDuration() {
        LocalDateTime localDate1 = LocalDateTime.of(2010, 10, 1, 10, 10, 10);
        LocalDateTime localDate2 = LocalDateTime.of(2010, 10, 2, 12, 10, 10);
        Duration d = Duration.between(localDate1, localDate2);
        System.out.println(d.getSeconds());

        Instant instant1 = Instant.now();
        Instant instant2 = Instant.now().plus(10, ChronoUnit.DAYS);
        Duration dd = Duration.between(instant1, instant2);
        System.out.println(dd.getSeconds());
    }

    /**
     * 可以以年月日等多个实践单位建模
     */
    @Test
    public void testPeriod() {
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println(tenDays.getDays());
    }

    @Test
    public void testTemporalAdjuster() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
        LocalDate date3 = date2.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
    }
}
