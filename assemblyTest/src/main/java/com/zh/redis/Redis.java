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

    public Redis(RedisConfig config) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(config.getMinIdle());
        jedisPoolConfig.setMaxIdle(config.getMaxIdle());
        jedisPoolConfig.setMaxTotal(config.getMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(config.getMaxWaitMillis());
        jedisPoolConfig.setFairness(config.isFairness());
        jedisPool = new JedisPool(jedisPoolConfig, config.getHost(), config.getPort(), config.getTimeout(), config.getPassword());
    }

    public void execute(RedisCallBack callBack) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            callBack.callback(jedis);
        } catch (JedisConnectionException e) {
            // 日志
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
