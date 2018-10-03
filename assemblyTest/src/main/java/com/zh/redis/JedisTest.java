package com.zh.redis;

/**
 *
 * @Author zh2683
 */
public class JedisTest {


    public static void main(String[] args) {
        Redis redis = new Redis("127.0.0.1", 6379, 20);
        Holder holder = new Holder();
        redis.execute(jedis -> {
            holder.counter = jedis.keys("*").size();
        });
        System.out.println(holder.counter);
    }
}
class Holder {
    public int counter;
}
