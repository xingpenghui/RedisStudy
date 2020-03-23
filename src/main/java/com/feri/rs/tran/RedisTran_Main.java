package com.feri.rs.tran;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @program: RedisStudy
 * @description:
 * @author: Feri
 * @create: 2020-03-23 14:46
 */
public class RedisTran_Main {
    public static void main(String[] args) {
        //获取客户端对象
        Jedis jedis=new Jedis("39.105.189.141",6379);
        jedis.auth("qfjava");
        jedis.select(4);
        //监视 key
        //jedis.watch("s5","s6","s7","s8");
        //开启事务
        Transaction transaction=jedis.multi();
        try {
            transaction.set("s5","Java基础");
            transaction.set("s6","JavaWeb");
            //故意导致失败 回滚事务
            System.out.println(1/0);
            transaction.set("s7","Java框架");
            System.out.println(transaction.set("s8","Java架构"));
            //提交事务
            transaction.exec();
        }catch (Exception e){
            //回滚
            transaction.discard();
        }
    }
}
