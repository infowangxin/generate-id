package com.wangxin.common.id;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangxin.common.id.redis.RedisIdGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/applicationContext-dao.xml" })
public class RedisIdGeneratorTest {

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
