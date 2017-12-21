package com.wangxin.common.id;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangxin.Application;
import com.wangxin.common.id.zk.ZookeeperIdGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ZookeeperIdGeneratorTest {

    @Autowired
    ZookeeperIdGenerator zookeeperIdGenerator;

    @Test
    public void getId() {
        try {
            System.err.println(zookeeperIdGenerator.nextSequenceId("T_NEWS", "X-Y"));// 从redis读取缓存的值
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
