package com.wangxin.common.id.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderNoLockServiceImpl implements OrderService {

    int num = 0;

    @Override
    public String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmHHss");
        return sdf.format(Calendar.getInstance().getTime()) + num++;
    }

}
