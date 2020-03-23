package com.feri.rs.pubsub;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program: RedisStudy
 * @description:
 * @author: Feri
 * @create: 2020-03-23 11:38
 */
public class PubSubMain {
    public static void main(String[] args) {
        //实例化连接池
        JedisPoolConfig config=new JedisPoolConfig();
        JedisPool pool=new JedisPool(config,"39.105.189.141",6379);
        String c="lxmsg";
        new SubThread(pool,c).start();
        new PublishThread(pool,c).start();
    }
}
