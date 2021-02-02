package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * @author: 钱恩强
 * @date: 2021/1/28 16:06
 */
public interface OrderDao {
    List<Order> findIdByCondition(Order order);

    void add(Order order);

    Map findById4Detail(Integer id);
    int getTodayOrderNumber(String date);
    int getTodayVisitsNumber(String date);
    int getThisWeekAndMonthOrderNumber(Map<String, Object> map);
    int getThisWeekAndMonthVisitsNumber(Map<String, Object> map);
    List<Map<String,Object>> findHotSetmeal();
}
