package com.wangxin.common.id.zk;

/** 
 * @Description 利用zookeeper获取全局唯一序号：</br>
 * @author 王鑫 
 * @date Dec 20, 2017 10:26:59 PM  
 */
public interface ZookeeperIdGenerator {
    
    /**
     * 获取全局唯一序号
     * @param name 资源名字
     * @param key 资源Key
     * @return 全局唯一序号
     * @throws Exception
     */
    String nextSequenceId(String name, String key) throws Exception;

    /**
     * 获取全局唯一序号
     * @param compositeKey 组合Key
     * @return 全局唯一序号
     * @throws Exception
     */
    String nextSequenceId(String compositeKey) throws Exception;

    /**
     * 排列获取全局唯一序号
     * @param name 资源名字
     * @param key 资源Key
     * @param count 批量获取数量 
     * @return 全局唯一序号
     * @throws Exception
     */
    String[] nextSequenceIds(String name, String key, int count) throws Exception;

    /**
     * 排列获取全局唯一序号
     * @param compositeKey 组合Key
     * @param count 批量获取数量
     * @return 全局唯一序号
     * @throws Exception
     */
    String[] nextSequenceIds(String compositeKey, int count) throws Exception;
}