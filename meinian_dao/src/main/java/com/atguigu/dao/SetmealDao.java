package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @author: 钱恩强
 * @date: 2021/1/25 10:54
 */
public interface SetmealDao {
    void setSetmealAndTravelGroup(Map<String, Integer> map);

    void add(Setmeal setmeal);

    Page<Setmeal> findPage(String queryString);
    void deleteSetmealAndTravelGroupBySetmealId(Integer id);
    void delete(Integer id);

    List<Setmeal> findAll();

    Setmeal findById(Integer id);

    List<Map<String, Object>> findSetmealCount();
}
