package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.MessageConstant;
import com.atguigu.dao.MemberDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Member;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderService;
import com.atguigu.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: 钱恩强
 * @date: 2021/1/28 16:05
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderDao orderDao;

    @Override
    public Result order(Map map) throws Exception {
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(date);
        if (orderSetting == null) {
            return new Result(false, MessageConstant.HAS_ORDERED);
        } else {
            int number = orderSetting.getNumber();
            int reservations = orderSetting.getReservations();
            if (reservations > number) {
                return new Result(false, MessageConstant.ORDER_SUCCESS);
            }
        }
        String telephone = (String) map.get("telephone");
        Member member = memberDao.findByTelephone(telephone);
        if (member != null) {
            Integer memberId = member.getId();
            int setmealId = Integer.parseInt((String) map.get("setmealId"));
            Order order = new Order(memberId, date, null, null, setmealId);
            List<Order> list = orderDao.findIdByCondition(order);
            if (list != null || list.size() > 0) {
                return new Result(false, MessageConstant.HAS_ORDERED);
            }
        } else {
            member = new Member();
            member.setName((String) map.get("name"));
            member.setSex((String) map.get("sex"));
            member.setPhoneNumber((String) map.get("telephone"));
            member.setIdCard((String) map.get("idCard"));
            member.setRegTime(new Date());
            memberDao.add(member);
        }
        orderSetting.setReservations(orderSetting.getReservations() + 1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);
        Order order = new Order();
        order.setMemberId(member.getId());
        order.setOrderDate(date);
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setOrderType((String) map.get("orderType"));
        order.setSetmealId(Integer.parseInt((String) map.get("setmealId")));
        orderDao.add(order);
        return new Result(true, MessageConstant.ORDERSETTING_SUCCESS, order);
    }


    @Override
    public Map findById4Detail(Integer id) throws Exception {
        Map map = orderDao.findById4Detail(id);
        Date orderDate = (Date) map.get("orderDate");
        map.put("orderDate", DateUtils.parseDate2String(orderDate));
        return map;
    }
}
