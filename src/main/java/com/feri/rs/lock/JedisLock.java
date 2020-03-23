package com.feri.rs.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: RedisStudy
 * @description:
 * @author: Feri
 * @create: 2020-03-23 15:59
 */
public class JedisLock {
    private JedisPool pool;
    public JedisLock(JedisPool pool){
        this.pool=pool;
    }
    //加锁
    /**
     * 通过Redis分布式锁
     * @param lname  锁的名称 key
     * @param getTime 获取锁的超时时间  单位毫秒
     * @param timeout 占用锁的超时时间 单位毫秒*/
    public String lock(String lname,long getTime,long timeout){
        Jedis jedis=pool.getResource();
        jedis.auth("qfjava");
        //唯一id
        String id=System.currentTimeMillis()+"_"+jedis.incr("unid");
        String r=null;
        //计算最大的获取锁的时间
        long max=System.currentTimeMillis()+getTime;
        Lock lock=new ReentrantLock();
        try{
            lock.lock();
            while (System.currentTimeMillis()<max){
                System.out.println("抢占锁："+Thread.currentThread().getId());
                //尝试加锁 如果成功 返回1  这个key之前不存在
                if(jedis.setnx(lname,id)==1){
                    jedis.expire(lname,(int)(timeout/1000));
                    r=id;
                    break;
                }else {
                    //锁被占用了 10毫秒之后，再次尝试
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }finally {
            lock.unlock();
        }
        return r;
    }
    //释放锁
    /**
     *@param lname 锁的名称
     *@param id  锁对应的唯一id*/
    public boolean unlock(String lname,String id){
        Jedis jedis=pool.getResource();
        jedis.auth("qfjava");
        Lock lock=new ReentrantLock();
        try {
            lock.lock();
            if(id.equals(jedis.get(lname))){
                //是同一个分布式锁
                jedis.del(lname);
                return true;
            }else {
                return false;
            }
        }finally {
            lock.unlock();
        }
    }
}