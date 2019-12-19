package com.zh;

import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangheng
 * @date 2019/12/04
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("com.zh.mapper")
@EnableCaching
@EnableDubbo(scanBasePackages = "com.zh.service")
@EnableRabbit
public class ServiceStarter {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ServiceStarter.class);
        new CountDownLatch(1).await();
    }
}
