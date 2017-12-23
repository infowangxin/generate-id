package com.wangxin.common.id.zk.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wangxin.common.id.zk.CuratorHandler;
import com.wangxin.common.id.zk.ZookeeperIdGenerator;

@Component
public class ZookeeperIdGeneratorImpl implements ZookeeperIdGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(ZookeeperIdGeneratorImpl.class);

    private static final int MAX_BATCH_COUNT = 1000;

    @Autowired
    private CuratorFramework curatorFramework;

    @Value("${prefix}")
    private String prefix;// redis key固定前缀，一般用于区分应用

    @Value("${frequentLogPrint:false}")
    private Boolean frequentLogPrint;// 是否开启频率的日志打印,默认不开启

    @Override
    public String nextSequenceId(String name, String key) throws Exception {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("name is null or empty");
        }

        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("key is null or empty");
        }

        String compositeKey = prefix + "_" + name + "_" + key;
        return nextSequenceId(compositeKey);
    }

    @Override
    public String nextSequenceId(String compositeKey) throws Exception {
        if (StringUtils.isEmpty(compositeKey)) {
            throw new RuntimeException("Composite key is null or empty");
        }

        if (!CuratorHandler.isStarted(curatorFramework)) {
            throw new RuntimeException("Curator isn't started");
        }
        String path = CuratorHandler.getPath(prefix, compositeKey);

        // 并发过快，这里会抛“节点已经存在”的错误，当节点存在时候，就不会创建，所以不必打印异常
        try {
            if (!CuratorHandler.pathExist(curatorFramework, path)) {
                CuratorHandler.createPath(curatorFramework, path, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {

        }

        int nextSequenceId = curatorFramework.setData().withVersion(-1).forPath(path, "".getBytes()).getVersion();
        if (frequentLogPrint) {
            LOG.info("Next sequenceId id is {} for key={}", nextSequenceId, compositeKey);
        }
        return String.valueOf(nextSequenceId);
    }

    @Override
    public String[] nextSequenceIds(String name, String key, int count) throws Exception {
        if (count <= 0 || count > MAX_BATCH_COUNT) {
            throw new RuntimeException(String.format("Count can't be greater than %d or less than 0", MAX_BATCH_COUNT));
        }

        String[] nextSequenceIds = new String[count];
        for (int i = 0; i < count; i++) {
            nextSequenceIds[i] = nextSequenceId(name, key);
        }
        return nextSequenceIds;
    }

    @Override
    public String[] nextSequenceIds(String compositeKey, int count) throws Exception {
        if (count <= 0 || count > MAX_BATCH_COUNT) {
            throw new RuntimeException(String.format("Count can't be greater than %d or less than 0", MAX_BATCH_COUNT));
        }
        String[] nextSequenceIds = new String[count];
        for (int i = 0; i < count; i++) {
            nextSequenceIds[i] = nextSequenceId(compositeKey);
        }
        return nextSequenceIds;
    }
}