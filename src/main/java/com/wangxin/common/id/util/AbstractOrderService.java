package com.wangxin.common.id.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class AbstractOrderService implements OrderService {

    static int num = 0;

    public static String getOrderNo2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmHHss");
        return sdf.format(Calendar.getInstance().getTime()) + num++;
    }

    @Override
    public String getOrderNo() {
        return getOrderNo2();
    }

}
