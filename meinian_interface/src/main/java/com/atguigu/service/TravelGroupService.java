package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;

import java.util.List;

/**
 * @author: 钱恩强
 * @date: 2021/1/24 15:04
 */
public interface TravelGroupService {
    void add(TravelGroup travelGroup, Integer[] travelItemIds);
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
    List<Integer> findTravelItemIdByTravelgroupId(Integer id);
    TravelGroup findById(Integer id);
    void edit(Integer[] travelitemIds,TravelGroup travelGroup) ;

    void delete(Integer id);

    List<TravelGroup> findAll();
}
