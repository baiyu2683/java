package com.zh.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 * @Author zh2683
 */
public class JedisTest {


    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(2);
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxWaitMillis(5000);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 5000, "zh2683");
        jedisPool.getResource().mset("asdf", "asdf", "123", "123");
    }
}
class Holder {
    public int counter;
}
