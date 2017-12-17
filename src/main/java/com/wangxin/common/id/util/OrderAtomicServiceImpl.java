package com.wangxin.common.id.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderAtomicServiceImpl implements OrderService{
    
    static AtomicInteger ai = new AtomicInteger();

    @Override
    public String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmHHss");
        return sdf.format(Calendar.getInstance().getTime()) + ai.incrementAndGet();
    }

}
