package com.zh.assembly.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Test;

public class ThreadSafeFormater {

    @Test
    public void timeStamp() {
        Instant instant = Instant.now();
        long seconds = instant.getEpochSecond();
        System.out.println(seconds);
    }
    
    @Test
    public void dateEdition8() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt.format(dtf));
    }
    
    @Test
    public void timeStampToDate() {
        Instant instant = Instant.now();
        Date date = new Date(instant.toEpochMilli());
        System.out.println(date);
        instant = date.toInstant();
    }
    
    @Test
    public void dateToLocalDate() {
        Date date = new Date();
        Instant instant = date.toInstant();
        
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        
        LocalDate ld = zdt.toLocalDate();
        System.out.println(ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        
        LocalDateTime ldt = zdt.toLocalDateTime();
        System.out.println(ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
