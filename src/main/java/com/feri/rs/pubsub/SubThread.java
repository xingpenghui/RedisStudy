package com.feri.rs.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program: RedisStudy
 * @description:
 * @author: Feri
 * @create: 2020-03-23 11:37
 */
public class SubThread extends Thread{
    private JedisPool pool;
    private String cnname;//通道名称
    private SubMsgListener listener;
    public SubThread(JedisPool pool,String name){
        this.pool=pool;
        cnname=name;
        listener=new SubMsgListener();
    }

    @Override
    public void run() {
        System.out.println("订阅者已经上线");
        Jedis jedis=pool.getResource();
        jedis.auth("qfjava");
        jedis.subscribe(listener,cnname);
    }
}
