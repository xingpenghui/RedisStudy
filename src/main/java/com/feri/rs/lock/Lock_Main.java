package com.feri.rs.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program: RedisStudy
 * @description:
 * @author: Feri
 * @create: 2020-03-23 16:20
 */
public class Lock_Main {
    public static void main(String[] args) {
        JedisPoolConfig config=new JedisPoolConfig();
        JedisPool pool=new JedisPool(config,"39.105.189.141",6379);
        JedisLock lock=new JedisLock(pool);
        RdissionLock lock1=new RdissionLock();
        new Thread(()->{
            for(int i=1;i<100;i++){
                String id=lock1.lock("lxlock");
                System.out.println(Thread.currentThread().getId()+"_加锁："+id+"__"+i);
                if(id!=null){
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getId()+"_释放锁："+lock1.unlock("lxlock"));
                }else {
                    System.out.println("加锁失败--"+id);
                }
            }
        }).start();
        new Thread(()->{
            for(int i=1;i<200;i++){
                String id=lock1.lock("lxlock");
                System.out.println(Thread.currentThread().getId()+"_加锁："+id+"__"+i);
                if(id!=null){
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getId()+"_释放锁："+lock1.unlock("lxlock"));
                }else {
                    System.out.println("加锁失败--"+id);
                }
            }
        }).start();
    }
}
