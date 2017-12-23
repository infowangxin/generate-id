package com.wangxin.common.id;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangxin.Application;
import com.wangxin.common.id.local.LocalIdGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
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
