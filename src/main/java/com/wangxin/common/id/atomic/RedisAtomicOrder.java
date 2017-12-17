package com.wangxin.common.id.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wangxin.common.id.task.OrderTask;
import com.wangxin.common.id.util.OrderRedisServiceImpl;
import com.wangxin.common.id.util.OrderService;

public class RedisAtomicOrder {

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(1);
        OrderService orderService = new OrderRedisServiceImpl();
        for (int i = 0; i < 10; i++) {
            es.submit(new OrderTask(latch, orderService));
        }
        latch.countDown();
        es.shutdown();
    }

}
