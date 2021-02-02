package com.atguigu.dao;

import com.atguigu.pojo.Address;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author: 钱恩强
 * @date: 2021/2/1 21:54
 */
public interface AddressDao {
    List<Address> findAll();

    Page<Address> selectByCondition(String queryString);

    void addAddress(Address address);

    void deleteById(Integer id);
}
