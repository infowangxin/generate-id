package com.wangxin.service.impl;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.wangxin.mapper.NewsMapper;
import com.wangxin.model.News;
import com.wangxin.service.NewsService;

/**
 * @author 王鑫
 * @Description 新闻接口实现类
 * @date Mar 16, 2017 5:19:24 PM
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {

    private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsMapper newsMapper;

    @Transactional
    @Override
    public boolean addNews(News news) {
        if (news != null) {
            // news.setId("1");
            news.setCreateTime(Calendar.getInstance().getTime());
            log.info("# {}", JSON.toJSONString(news));
            int flag = newsMapper.insert(news);
            if (flag == 1)
                return true;
            else
                return false;
        } else
            return false;
    }
}
