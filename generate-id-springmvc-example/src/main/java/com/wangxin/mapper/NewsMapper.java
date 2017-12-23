package com.wangxin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wangxin.model.News;


/** 
 * @Description 新闻mapper接口
 * @author 王鑫 
 * @date Mar 16, 2017 3:35:19 PM  
 */
@Mapper
public interface NewsMapper {

    int insert(News news);
}
