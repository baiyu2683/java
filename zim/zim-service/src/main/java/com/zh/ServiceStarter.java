package com.zh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author zhangheng
 * @date 2019/12/04
 */
@SpringBootApplication
@MapperScan("com.zh.mapper")
@EnableCaching
public class ServiceStarter {

    public static void main(String[] args) {
        SpringApplication.run(ServiceStarter.class);
    }
}
