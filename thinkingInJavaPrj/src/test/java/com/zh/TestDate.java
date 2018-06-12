package com.zh;

import org.junit.Test;

/**
 * Created by zh on 2017-07-31.
 */
public class TestDate {

    @Test
    public void testDate() {
        //一天的毫秒数
        long day = 1000 * 60 * 60 * 24;
        long hour = 1000 * 60 * 60;
        long minute = 1000 * 60;
        long second = 1000;
        long interval = 86501000l;
        long dayx = interval / day;
        long houry = interval % day;
        long hourx = houry / hour;
        long minutey = houry % hour;
        long minutex = minutey / minute;
        long secondy = minutey % minute;
        long secondx = secondy / second;
        System.out.println(dayx + "-" + hourx + "-" + minutex + "-" + secondx);
    }
}
