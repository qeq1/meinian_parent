package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 钱恩强
 * @date: 2021/1/26 18:34
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> orderSettings) {
        for (OrderSetting orderSetting : orderSettings) {
            //先用日期查询数据库是中是否存在
            long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
            if (count > 0) {
                //若存在执行修改操作
                orderSettingDao.editNumberByOrderDate(orderSetting);
            } else {
                //不存在执行添加操作
                orderSettingDao.add(orderSetting);
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        //通过拼接得到当月1号
        String dateBegin = date + "-1";
        //通过拼接得到当月最后一天
        String dateEnd = date + "-31";
        //将拼接得到的放入到map集合中
        Map<String, Object> map = new HashMap<>();
        map.put("dateBegin", dateBegin);
        map.put("dateEnd", dateEnd);
        //调用获取当月的数据存放到list集合中
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            //将获取到的数据封装成ordersetting存放到map集合中
            orderSettingMap.put("date", orderSetting.getOrderDate().getDate());
            orderSettingMap.put("number", orderSetting.getNumber());
            orderSettingMap.put("reservations", orderSetting.getReservations());
            //添加到类型为map的Listj集合返回
            data.add(orderSettingMap);
        }
        return data;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        //先通过日期进行查询
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count > 0) {
            //如果数据库中已经存在则进行修改
            orderSettingDao.editNumberByOrderDate(orderSetting);
        } else {
            //如果不存在则进行添加
            orderSettingDao.add(orderSetting);
        }
    }
}
