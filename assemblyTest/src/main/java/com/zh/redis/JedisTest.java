package com.zh.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @Author zh2683
 */
public class JedisTest {


    public static void main(String[] args) {
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setMinIdle(4);
        redisConfig.setMaxIdle(10);
        redisConfig.setMaxTotal(100);
        redisConfig.setMaxWaitMillis(10000);
        redisConfig.setTimeout(10000);

        redisConfig.setHost("127.0.0.1");
        redisConfig.setPassword("12345");
        redisConfig.setPort(6379);

        int counter = 10000;

        Map<String, String> maps = new HashMap<>();
        for (int i = 0 ; i < counter ; i++) {
            maps.put(UUID.randomUUID().toString(), ((Math.random() * 100) / 10) + "");
        }
        Redis redis = new Redis(redisConfig);
        long start = System.currentTimeMillis();
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            redis.execute(jedis -> {
                jedis.set(entry.getKey(), entry.getValue());
            });
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
class Holder {
    public int counter;
}
