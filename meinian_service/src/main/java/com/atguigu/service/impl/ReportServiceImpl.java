package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.service.ReportService;
import com.atguigu.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 钱恩强
 * @date: 2021/1/30 21:47
 */
@Service(interfaceClass = ReportService.class)
@Transactional
public  class ReportServiceImpl implements ReportService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MemberDao memberDao;
    @Override
    public Map<String, Object> getBusinessReportData() {
        Map<String,Object> map=null;
        try {
            String today= DateUtils.parseDate2String(DateUtils.getToday());
            String weekMonday=DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
            String weekSunday=DateUtils.parseDate2String(DateUtils.getSundayOfThisWeek());
            String monthFirst = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
            String monthLast=DateUtils.parseDate2String(DateUtils.getLastDay4ThisMonth());
            int todayNewMember = memberDao.getTodayNewMember(today);
            int totalMember = memberDao.getTotalMember();
            int thisWeekNewMember = memberDao.getThisWeekAndMonthNewMember(weekMonday);
            int thisMonthNewMember = memberDao.getThisWeekAndMonthNewMember(monthFirst);
            int todayOrderNumber = orderDao.getTodayOrderNumber(today);
            int todayVisitsNumber = orderDao.getTodayVisitsNumber(today);
            Map<String,Object> paramWeek=new HashMap<>();
            paramWeek.put("begin",weekMonday);
            paramWeek.put("end",weekSunday);
            int thisWeekOrderNumber=orderDao.getThisWeekAndMonthOrderNumber(paramWeek);

            Map<String,Object> paramMonth=new HashMap<String,Object>();
            paramMonth.put("begin",monthFirst);
            paramMonth.put("end",monthLast);
            int thisMonthOrderNumber = orderDao.getThisWeekAndMonthOrderNumber(paramMonth);

            Map<String,Object> paramWeekVisit=new HashMap<String,Object>();
            paramWeekVisit.put("begin",weekMonday);
            paramWeekVisit.put("end",weekSunday);
            int thisWeekVisitsNumber = orderDao.getThisWeekAndMonthVisitsNumber(paramWeekVisit);

            Map<String,Object> paramMonthVisit=new HashMap<String,Object>();
            paramMonth.put("begin",weekMonday);
            paramMonth.put("end",weekSunday);
            int thisMonthVisitsNumber = orderDao.getThisWeekAndMonthVisitsNumber(paramMonthVisit);

            List<Map<String,Object>> hotSetmeal=orderDao.findHotSetmeal();
            map = new HashMap<String,Object>();
            map.put("reportDate",today);
            map.put("todayNewMember",todayNewMember);
            map.put("totalMember",totalMember);
            map.put("thisWeekNewMember",thisWeekNewMember);
            map.put("thisMonthNewMember",thisMonthNewMember);
            map.put("todayOrderNumber",todayOrderNumber);
            map.put("todayVisitsNumber",todayVisitsNumber);
            map.put("thisWeekOrderNumber",thisWeekOrderNumber);
            map.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
            map.put("thisMonthOrderNumber",thisMonthOrderNumber);
            map.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
            map.put("hotSetmeal",hotSetmeal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
