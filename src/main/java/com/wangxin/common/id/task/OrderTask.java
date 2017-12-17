package com.wangxin.common.id.task;

import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import com.wangxin.common.id.util.OrderService;

public class OrderTask implements Runnable {
    CountDownLatch latch;
    OrderService orderService;
    InterProcessMutex interProcessMutex;

    public OrderTask(CountDownLatch latch, OrderService orderService) {
        super();
        this.latch = latch;
        this.orderService = orderService;
    }

    public OrderTask(CountDownLatch latch, OrderService orderService, InterProcessMutex interProcessMutex) {
        super();
        this.latch = latch;
        this.orderService = orderService;
        this.interProcessMutex = interProcessMutex;
    }

    @Override
    public void run() {
        try {
            latch.await();
            //interProcessMutex.acquire();
            System.out.println(Thread.currentThread().getName() + "\t订单号：" + orderService.getOrderNo());
            //interProcessMutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
