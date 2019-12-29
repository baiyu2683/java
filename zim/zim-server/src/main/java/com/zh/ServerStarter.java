package com.zh;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author zhangheng
 * @date 2019/12/03
 */
@SpringBootApplication
public class ServerStarter {

    private static final Logger logger = LoggerFactory.getLogger(ServerStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(ServerStarter.class);
    }
}
