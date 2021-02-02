package com.atguigu.service;


import com.atguigu.pojo.User;

/**
 * @author: 钱恩强
 * @date: 2021/1/29 22:30
 */
public interface UserService {
    User findUserByUsername(String username);
}
