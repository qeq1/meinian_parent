package com.atguigu.dao;

import com.atguigu.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author: 钱恩强
 * @date: 2021/1/29 22:33
 */
@Repository
public interface UserDao {
    User findUserByUsername(String username);
}
