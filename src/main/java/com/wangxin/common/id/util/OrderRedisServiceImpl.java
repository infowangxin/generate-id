package com.wangxin.common.id.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class OrderRedisServiceImpl implements OrderService {

    static JedisPool jedisPool;
    static {
        jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379, 1000, null);
    }

    @Override
    public String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmHHss");
        return sdf.format(Calendar.getInstance().getTime()) + jedisPool.getResource().incr("order_no");
    }

}
