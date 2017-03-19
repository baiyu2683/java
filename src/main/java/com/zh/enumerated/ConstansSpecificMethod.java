package com.zh.enumerated;

import java.text.DateFormat;
import java.util.Date;

/**
 * 常量相关的方法
 * Created by zh on 2017-03-19.
 */
public enum ConstansSpecificMethod {
    DATE_TIME{
        @Override
        String getInfo() {
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH {
        String getInfo() {
            return System.getenv("classpath");
        }
    },
    VERSION {
        String getInfo() {
            return System.getProperty("java.version");
        }
    };
    abstract String getInfo();

    public static void main(String[] args) {
        for (ConstansSpecificMethod constansSpecificMethod : values()) {
            System.out.println(constansSpecificMethod.getInfo());
        }
    }
}
