package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberDao;
import com.atguigu.pojo.Member;
import com.atguigu.service.MemberService;
import com.atguigu.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 钱恩强
 * @date: 2021/1/30 20:07
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public List<Integer> findMemberCountByMonth(List<String> monthsList) {
        List<Integer> memberCountList = new ArrayList<>();
        if (monthsList != null && monthsList.size() > 0) {
            for (String months :
                    monthsList) {
                String regTime = DateUtils.getLastDayOfMonth(months);
                Integer memberCount=memberDao.findMemberCountByMonth(regTime);
                memberCountList.add(memberCount);
            }
        }
        return memberCountList;
    }

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        memberDao.add(member);
    }
}
