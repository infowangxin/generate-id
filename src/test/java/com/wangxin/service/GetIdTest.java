package com.wangxin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangxin.Application;
import com.wangxin.common.id.redis.RedisIdGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class GetIdTest {

    @Autowired
    RedisIdGenerator redisIdGenerator;

    @Test
    public void getId() {
        try {
            System.err.println(redisIdGenerator.nextUniqueId("T_NEWS"));// 从redis读取缓存的值
            System.err.println(redisIdGenerator.nextUniqueId("T_NEWS"));// 从redis读取缓存的值
            System.err.println(redisIdGenerator.nextUniqueId("T_SYS_USER"));// 从redis读取缓存的值
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
