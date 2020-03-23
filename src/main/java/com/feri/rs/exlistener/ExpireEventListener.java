package com.feri.rs.exlistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

/**
 * @program: RedisStudy
 * @description:
 * @author: Feri
 * @create: 2020-03-23 15:10
 */
public class ExpireEventListener extends JedisPubSub {
    private Logger logger= LoggerFactory.getLogger(ExpireEventListener.class);

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("获取消息："+message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        logger.info("Redis订阅超时key失效监听："+message);

    }
}
