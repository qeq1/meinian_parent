package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @author: 钱恩强
 * @date: 2021/1/25 10:54
 */
public interface SetmealService {
    void add(Setmeal setmeal, Integer[] travelgroupId);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
    void delete(Integer id);

    List<Setmeal> findAll();

    Setmeal findById(Integer id);

    List<Map<String, Object>> findSetmealCount();
}
