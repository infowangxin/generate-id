package com.wangxin.common.id.zk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ExistsBuilder;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryForever;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.curator.utils.PathUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuratorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CuratorHandler.class);

    public static final String ENCODING_UTF_8 = "UTF-8";

    // 重试指定的次数, 且每一次重试之间停顿的时间逐渐增加
    public static RetryPolicy createExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);

        return retryPolicy;
    }

    // 重试指定的次数, 且每一次重试之间停顿的时间逐渐增加，增加了最大重试次数的控制
    public static RetryPolicy createBoundedExponentialBackoffRetry(int baseSleepTimeMs, int maxSleepTimeMs, int maxRetries) {
        RetryPolicy retryPolicy = new BoundedExponentialBackoffRetry(baseSleepTimeMs, maxSleepTimeMs, maxRetries);

        return retryPolicy;
    }

    // 指定最大重试次数的重试
    public static RetryPolicy createRetryNTimes(int count, int sleepMsBetweenRetries) {
        RetryPolicy retryPolicy = new RetryNTimes(count, sleepMsBetweenRetries);

        return retryPolicy;
    }

    // 永远重试
    public static RetryPolicy createRetryForever(int retryIntervalMs) {
        RetryPolicy retryPolicy = new RetryForever(retryIntervalMs);
        return retryPolicy;
    }

    // 一直重试，直到达到规定的时间
    public static RetryPolicy createRetryUntilElapsed(int maxElapsedTimeMs, int sleepMsBetweenRetries) {
        RetryPolicy retryPolicy = new RetryUntilElapsed(maxElapsedTimeMs, sleepMsBetweenRetries);
        return retryPolicy;
    }

    // 创建ZooKeeper客户端实例
    public static CuratorFramework createCurator(String connectString, int sessionTimeoutMs, int connectionTimeoutMs, RetryPolicy retryPolicy) {
        LOG.info("Start to initialize Curator..");
        CuratorFramework curator = CuratorFrameworkFactory.newClient(connectString, sessionTimeoutMs, connectionTimeoutMs, retryPolicy);
        return curator;
    }

    // 启动ZooKeeper客户端
    public static void startCurator(CuratorFramework curator) {
        LOG.info("Start Curator...");
        curator.start();
    }

    // 启动ZooKeeper客户端，直到第一次连接成功
    public static void startAndBlockCurator(CuratorFramework curator) throws InterruptedException {
        LOG.info("start and block Curator...");
        curator.start();
        curator.blockUntilConnected();
    }

    // 启动ZooKeeper客户端，直到第一次连接成功，为每一次连接配置超时
    public static void startAndBlockCurator(CuratorFramework curator, int maxWaitTime, TimeUnit units) throws InterruptedException {
        LOG.info("start and block Curator...");
        curator.start();
        curator.blockUntilConnected(maxWaitTime, units);
    }

    // 关闭ZooKeeper客户端连接
    public static void closeCurator(CuratorFramework curator) {
        LOG.info("Start to close Curator...");
        curator.close();
    }

    // 获取ZooKeeper客户端连接是否正常
    public static boolean isStarted(CuratorFramework curator) {
        return curator.getState() == CuratorFrameworkState.STARTED;
    }

    // 判断路径是否存在
    public static boolean pathExist(CuratorFramework curator, String path) throws Exception {
        return getPathStat(curator, path) != null;
    }

    // 判断stat是否存在
    public static Stat getPathStat(CuratorFramework curator, String path) throws Exception {
        PathUtils.validatePath(path);

        ExistsBuilder builder = curator.checkExists();
        if (builder == null) {
            return null;
        }

        Stat stat = builder.forPath(path);
        return stat;
    }

    // 创建路径
    public static void createPath(CuratorFramework curator, String path) throws Exception {
        PathUtils.validatePath(path);
        curator.create().creatingParentsIfNeeded().forPath(path, null);
    }

    // 创建路径，并写入数据
    public static void createPath(CuratorFramework curator, String path, byte[] data) throws Exception {
        PathUtils.validatePath(path);
        curator.create().creatingParentsIfNeeded().forPath(path, data);
    }

    // 创建路径
    public static void createPath(CuratorFramework curator, String path, CreateMode mode) throws Exception {
        PathUtils.validatePath(path);
        curator.create().creatingParentsIfNeeded().withMode(mode).forPath(path, null);
    }

    // 创建路径，并写入数据
    public static void createPath(CuratorFramework curator, String path, byte[] data, CreateMode mode) throws Exception {
        PathUtils.validatePath(path);
        curator.create().creatingParentsIfNeeded().withMode(mode).forPath(path, data);
    }

    // 删除路径
    public static void deletePath(CuratorFramework curator, String path) throws Exception {
        PathUtils.validatePath(path);

        curator.delete().deletingChildrenIfNeeded().forPath(path);
    }

    // 获取子节点名称列表
    public static List<String> getChildNameList(CuratorFramework curator, String path) throws Exception {
        PathUtils.validatePath(path);
        return curator.getChildren().forPath(path);
    }

    // 获取子节点路径列表
    public static List<String> getChildPathList(CuratorFramework curator, String path) throws Exception {
        List<String> childNameList = getChildNameList(curator, path);

        List<String> childPathList = new ArrayList<String>();
        for (String childName : childNameList) {
            String childPath = path + "/" + childName;
            childPathList.add(childPath);
        }
        return childPathList;
    }

    // 组装根节点路径
    public static String getRootPath(String prefix) {
        return "/" + prefix;
    }

    // 组装节点路径
    public static String getPath(String prefix, String key) {
        return "/" + prefix + "/" + key;
    }
}