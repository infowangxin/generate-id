package com.wangxin.common.id.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wangxin.common.id.task.OrderTask;
import com.wangxin.common.id.util.OrderAtomicServiceImpl;
import com.wangxin.common.id.util.OrderService;

/** 
 * @Description 原子ID生成器
 * @author 王鑫 
 * @date Dec 15, 2017 10:38:41 PM  
 */
public class JvmAtomicOrder {

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(1);
        OrderService orderService =  new OrderAtomicServiceImpl();
        for (int i = 0; i < 10; i++) {
            es.submit(new OrderTask(latch, orderService));
        }
        latch.countDown();
        es.shutdown();
    }

}
