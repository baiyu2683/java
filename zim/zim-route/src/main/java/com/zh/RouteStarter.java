package com.zh;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.PropertySource;

@EnableHystrix
@SpringBootApplication
@EnableDubbo
@PropertySource("classpath:dubbo.properties")
public class RouteStarter
{
    public static void main( String[] args ) {
        SpringApplication.run(RouteStarter.class);
    }
}
