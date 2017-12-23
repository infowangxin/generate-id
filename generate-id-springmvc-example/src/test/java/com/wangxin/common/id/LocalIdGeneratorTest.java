package com.wangxin.common.id;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangxin.common.id.local.LocalIdGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/applicationContext-dao.xml" })
public class LocalIdGeneratorTest {

    @Autowired
    LocalIdGenerator localIdGenerator;

    @Test
    public void getId() {
        try {
            System.err.println(localIdGenerator.nextUniqueId(2, 3));// 从zookeeper读取缓存的值
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
