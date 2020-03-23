package com.feri.rs.lock;

import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @program: RedisStudy
 * @description:
 * @author: Feri
 * @create: 2020-03-23 16:34
 */
public class RdissionLock {
    private RedissonClient client;
    public RdissionLock(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://39.105.189.141:6379").setPassword("qfjava");
        client=Redisson.create(config);
        System.out.println(client.getClass());
    }
    //加锁
    public String lock(String lname){
        RedissonLock l;
        RLock rLock=client.getLock(lname);
        System.out.println(rLock.getClass());
        rLock.lock();
        return rLock.getName();
    }
    //释放锁
    public boolean unlock(String lname){
        client.getLock(lname).unlock();
        return true;
    }
}
