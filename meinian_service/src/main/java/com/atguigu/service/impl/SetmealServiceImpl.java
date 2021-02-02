package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.RedisConstant;
import com.atguigu.dao.SetmealDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 钱恩强
 * @date: 2021/1/25 10:53
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(Setmeal setmeal, Integer[] travelgroupId) {
        setmealDao.add(setmeal);
        if (travelgroupId != null && travelgroupId.length > 0) {
            setSetmealAndTravelGroup(setmeal.getId(), travelgroupId);
        }
        savePic2Redis(setmeal.getImg());
    }

    private void savePic2Redis(String pic) {
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, pic);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void delete(Integer id) {
        setmealDao.deleteSetmealAndTravelGroupBySetmealId(id);
        setmealDao.delete(id);
    }

    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    @Override
    public List<Map<String, Object>> findSetmealCount() {
        return setmealDao.findSetmealCount();
    }

    private void setSetmealAndTravelGroup(Integer id, Integer[] travelgroupId) {
        for (Integer checkgroudId : travelgroupId) {
            Map<String, Integer> map = new HashMap<>();
            map.put("travelgroup_id", checkgroudId);
            map.put("setmeal_id", id);
            setmealDao.setSetmealAndTravelGroup(map);
        }
    }

}
