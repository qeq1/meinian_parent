package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: 钱恩强
 * @date: 2021/1/26 18:35
 */
public interface OrderSettingDao {
    long findCountByOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMonth(Map map);


    void editReservationsByOrderDate(OrderSetting orderSetting);

    OrderSetting findByOrderDate(Date date);
}


