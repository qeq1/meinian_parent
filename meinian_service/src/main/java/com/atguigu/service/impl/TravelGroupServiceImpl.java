package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;

import java.util.List;
import java.util.Map;
import com.atguigu.service.TravelGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;

/**
 * @author: 钱恩强
 * @date: 2021/1/24 15:04
 */
@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {
    @Autowired
    private TravelGroupDao travelGroupDao;
    @Override
    public void add(TravelGroup travelGroup, Integer[] travelItemIds) {
        travelGroupDao.add(travelGroup);
        setTravelGroupAndTravelItem(travelGroup.getId(),travelItemIds);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<TravelGroup> page = travelGroupDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Integer> findTravelItemIdByTravelgroupId(Integer id) {
        return travelGroupDao.findTravelItemIdByTravelgroupId(id);
    }

    @Override
    public TravelGroup findById(Integer id) {
        return travelGroupDao.findById(id);
    }

    @Override
    public void edit(Integer[] travelitemIds,TravelGroup travelGroup) {
        travelGroupDao.edit(travelGroup);
        travelGroupDao.deleteTravelGroupAndTravelItemByTravelGroupId(travelGroup.getId());
        setTravelGroupAndTravelItem(travelGroup.getId(),travelitemIds);
    }

    @Override
    public void delete(Integer id) {
        travelGroupDao.deleteTravelGroupAndTravelItemByTravelGroupId(id);
        travelGroupDao.delete(id);
    }

    @Override
    public List<TravelGroup> findAll() {
        return travelGroupDao.findAll();
    }

    private void setTravelGroupAndTravelItem(Integer traveGroupId,Integer[] travelItemIds){
        if(traveGroupId!=null&&travelItemIds.length>0){
            for(Integer travelItemId:travelItemIds){
                Map<String,Integer> map=new HashMap<>();
                map.put("travelGroup",traveGroupId);
                map.put("travelItem",travelItemId);
                travelGroupDao.setTravelGroupAndTravelItem(map);
            }
        }
    }
}
