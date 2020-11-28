package com.zh.edition8;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class TemporalFieldTest {

    @Test
    public void test1() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.get(ChronoField.YEAR));
        System.out.println(localDate.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(localDate.get(ChronoField.DAY_OF_MONTH));
    }
}
