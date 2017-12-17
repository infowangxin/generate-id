package com.wangxin.common.id.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.wangxin.common.id.task.OrderTask;
import com.wangxin.common.id.util.OrderNoLockServiceImpl;
import com.wangxin.common.id.util.OrderService;

public class CuratorLockOrder {

    final static CuratorFramework cf = CuratorFrameworkFactory.builder().connectString("localhost:2281,localhost:2381,localhost:2481").retryPolicy(new ExponentialBackoffRetry(100, 10000)).build();

    public static void main(String[] args) {
        cf.start();
        ExecutorService es = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(1);
        final InterProcessMutex lock = new InterProcessMutex(cf, "/t11026");
        OrderService orderService = new OrderNoLockServiceImpl();
        for (int i = 0; i < 10; i++) {
            es.submit(new OrderTask(latch, orderService, lock));
        }
        latch.countDown();
        es.shutdown();
    }

}
