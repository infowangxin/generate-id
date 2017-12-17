package com.wangxin.common.id.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wangxin.common.id.task.OrderTask;
import com.wangxin.common.id.util.OrderLockServiceImpl;
import com.wangxin.common.id.util.OrderService;

public class JvmLockOrder {

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            OrderService orderService = new OrderLockServiceImpl();
            es.submit(new OrderTask(latch, orderService));
        }
        latch.countDown();
        es.shutdown();
    }
}
