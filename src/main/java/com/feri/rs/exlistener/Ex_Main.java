package com.feri.rs.exlistener;

import redis.clients.jedis.Jedis;

/**
 * @program: RedisStudy
 * @description:
 * @author: Feri
 * @create: 2020-03-23 15:14
 */
public class Ex_Main {
    public static void main(String[] args) {
        //实例化客户端
        Jedis jedis=new Jedis("39.105.189.141",6379);
        jedis.auth("qfjava");
        System.out.println("失效监听中……");
        ExpireEventListener listener=new ExpireEventListener();
        //失效通道：__keyevent@ 固定 事件  0表示库(默认16个库) expired 失效事件
        jedis.subscribe(listener,"__keyevent@0__:expired");
    }
}
