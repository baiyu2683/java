package com.zh.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhangheng
 * @date 2019/12/13
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void get() {
        Object object = redisTemplate.opsForValue().get("user::500000");
        System.out.println(object.getClass());
    }
}
