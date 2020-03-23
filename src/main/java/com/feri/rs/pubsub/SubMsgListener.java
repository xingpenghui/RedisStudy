package com.feri.rs.pubsub;

import redis.clients.jedis.JedisPubSub;

/**
 * @program: RedisStudy
 * @description: Redis 的获取 发布订阅的监听
 * @author: Feri
 * @create: 2020-03-23 11:44
 */
public class SubMsgListener extends JedisPubSub {
    //获取消息
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("接收消息：通道名称 %s，消息内容 %s",channel,message));
    }

    //订阅通道
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("订阅通道：通道名称 %s",channel));
    }

    //取消订阅
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("取消订阅：通道名称 %s",channel));
    }
}
