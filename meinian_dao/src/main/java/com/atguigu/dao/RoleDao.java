package com.atguigu.dao;

import com.atguigu.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author: 钱恩强
 * @date: 2021/1/29 22:34
 */
@Repository
public interface RoleDao {
    Set<Role> findRolesByUserId(Integer userId);
}
