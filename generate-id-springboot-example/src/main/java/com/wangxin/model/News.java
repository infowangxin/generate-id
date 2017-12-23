package com.wangxin.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 新闻对象
 * @author 王鑫
 * @date Mar 16, 2017 3:25:39 PM
 */
public class News implements Serializable {

    private static final long serialVersionUID = 3624947930970250778L;

    private String id;

    private String title;

    private String description;

    private String address;

    private Date newsTime;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
