package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;
/**
 * @author: 钱恩强
 * @date: 2021/1/24 15:05
 */
public interface TravelGroupDao {
    void add(TravelGroup travelGroup);
    void setTravelGroupAndTravelItem(Map<String,Integer> map);
    Page<TravelGroup> findPage(String queryString);
    List<Integer> findTravelItemIdByTravelgroupId(Integer id);
    TravelGroup findById(Integer id);
    void edit(TravelGroup travelGroup);
    void deleteTravelGroupAndTravelItemByTravelGroupId(Integer id);
    void delete(Integer id);
    List<TravelGroup> findAll();
    List<TravelGroup> findTravelGroupListById(Integer id);
}
