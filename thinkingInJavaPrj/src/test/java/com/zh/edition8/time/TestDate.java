package com.zh.edition8.time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by zh on 2017-08-01.
 */
public class TestDate {

    @Test
    public void test() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.systemDefault());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(localDateTime.format(dateTimeFormatter));
    }
}
