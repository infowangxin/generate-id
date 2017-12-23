package com.wangxin.common.id;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangxin.common.id.zk.ZookeeperIdGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/applicationContext-dao.xml" })
public class ZookeeperIdGeneratorTest {

    @Autowired
    ZookeeperIdGenerator zookeeperIdGenerator;

    @Test
    public void getId() {
        boolean flag = true;
        do {
            try {
                System.err.println(zookeeperIdGenerator.nextSequenceId("T_NEWS"));// 从redis读取缓存的值
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }
        } while (flag);
    }

}
