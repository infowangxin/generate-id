package com.wangxin.common.id.zk;

public interface ZookeeperIdGenerator {
    /**
     * 获取全局唯一序号
     * @param name 资源名字
     * @param key 资源Key
     * @return
     * @throws Exception
     */
    String nextSequenceId(String name, String key) throws Exception;

    String nextSequenceId(String compositeKey) throws Exception;

    String[] nextSequenceIds(String name, String key, int count) throws Exception;

    String[] nextSequenceIds(String compositeKey, int count) throws Exception;
}