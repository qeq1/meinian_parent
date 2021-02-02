package com.atguigu.dao;

import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author: 钱恩强
 * @date: 2021/1/22 19:33
 */
public interface TravelItemDao {
    void add(TravelItem travelItem);
    Page<TravelItem> findPage(String queryString);
    long findCountByTravelItemItemId(Integer id);
    void deleteById(Integer id);
    TravelItem findById(Integer id);
    void edit(TravelItem travelItem);
    List<TravelItem> findAll();
    List<TravelItem> findTravelItemListById(Integer id);
}
