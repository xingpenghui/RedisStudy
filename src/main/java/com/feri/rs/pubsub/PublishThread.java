package com.feri.rs.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @program: RedisStudy
 * @description: 基于Redis 消息发布
 * @author: Feri
 * @create: 2020-03-23 11:37
 */
public class PublishThread extends Thread {
    private JedisPool pool;
    private String cnname;//通道名称
    public PublishThread(JedisPool pool,String name){
        this.pool=pool;
        cnname=name;
    }
    @Override
    public void run() {
        Jedis jedis=pool.getResource();
        jedis.auth("qfjava");
        //获取键盘输入 System.in
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String msg;
        try {
            while (true){
                //获取小
                msg=reader.readLine();
                //发布消息
                jedis.publish(cnname, msg);
                System.out.println("发布："+msg);

                if(msg.equals("886")){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
