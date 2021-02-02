package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelItem;

import java.util.List;

/**
 * @author: 钱恩强
 * @date: 2021/1/22 19:30
 */
public interface TravelItemService {
    void add(TravelItem travelItem);
    PageResult findPage(Integer currentPage,Integer pageSize,String queryString);
    void deleteById(Integer id);
    TravelItem findById(Integer id);
    void edit(TravelItem travelItem);
    List<TravelItem> findAll();
}
