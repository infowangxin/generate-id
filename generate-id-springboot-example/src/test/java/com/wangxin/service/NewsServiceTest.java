package com.wangxin.service;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangxin.Application;
import com.wangxin.common.id.redis.RedisIdGenerator;
import com.wangxin.mapper.NewsMapper;
import com.wangxin.model.News;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class NewsServiceTest {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    RedisIdGenerator redisIdGenerator;

    @Test
    public void addNews() {
        boolean flag = true;
        do {
            try {
                String id = redisIdGenerator.nextUniqueId("idgenerater");
                System.out.println(id);
                News news = new News();
                news.setId(id);
                news.setTitle("test");
                news.setDescription("test");
                news.setAddress("test");
                news.setNewsTime(Calendar.getInstance().getTime());
                news.setCreateTime(Calendar.getInstance().getTime());
                newsMapper.insert(news);
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }
        } while (flag);
    }
}
