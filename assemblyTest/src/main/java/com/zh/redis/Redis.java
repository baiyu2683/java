package com.zh.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;


/**
 * redis客户端实现
 * @Author zh2683
 */
public class Redis {

    private JedisPool jedisPool;

    public Redis(String host, int port, int timeout) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxWaitMillis(10);
        jedisPoolConfig.setFairness(false);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(jedisPoolConfig,host, port, timeout, "zh2683");
    }

    public void execute(RedisCallBack callBack) {
        Jedis jedis = jedisPool.getResource();
        try {
            callBack.callback(jedis);
        } catch (JedisConnectionException e) {
            callBack.callback(jedis);
        } finally {
            jedis.close();
        }
    }
}
