package com.atguigu.service;

import com.atguigu.pojo.Member;

import java.util.List;

/**
 * @author: 钱恩强
 * @date: 2021/1/30 20:07
 */
public interface MemberService {
    List<Integer> findMemberCountByMonth(List<String> list);

    Member findByTelephone(String telephone);

    void add(Member member);
}


