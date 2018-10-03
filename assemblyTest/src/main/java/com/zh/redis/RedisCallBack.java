package com.zh.redis;

import redis.clients.jedis.Jedis;

/**
 * @Author zh2683
 */
public interface RedisCallBack {

    void callback(Jedis jedis);
}
