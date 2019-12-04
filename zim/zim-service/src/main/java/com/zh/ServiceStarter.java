package com.zh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangheng
 * @date 2019/12/04
 */
@Slf4j
@SpringBootApplication
public class ServiceStarter {

    public static void main(String[] args) {
        SpringApplication.run(ServiceStarter.class);
    }
}
