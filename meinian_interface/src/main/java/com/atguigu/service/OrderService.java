package com.atguigu.service;

import com.atguigu.entity.Result;

import java.util.Map;

/**
 * @author: 钱恩强
 * @date: 2021/1/28 16:05
 */
public interface OrderService {
    Result order(Map map) throws Exception;

    Map findById4Detail(Integer id) throws Exception;
}
