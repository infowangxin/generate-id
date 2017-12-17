package com.wangxin.common.id.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderLockServiceImpl extends AbstractOrderService implements OrderService {

    static int num = 0;

    @Override
    synchronized public String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmHHss");
        return sdf.format(Calendar.getInstance().getTime()) + num++;
    }

}
