package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: 钱恩强
 * @date: 2021/1/24 15:04
 */
@RestController
@RequestMapping("/travelgroup")
public class TravelGroupController {
    @Reference
    private TravelGroupService travelGroupService;

    @RequestMapping("/add")
    public Result add(@RequestBody TravelGroup travelGroup, Integer[] travelItemIds) {
        travelGroupService.add(travelGroup, travelItemIds);
        return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = travelGroupService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
        return pageResult;
    }
    @RequestMapping("/findTravelItemIdByTravelgroupId")
    public List<Integer> findTravelItemIdByTravelgroupId(Integer id){
        List<Integer> list = travelGroupService.findTravelItemIdByTravelgroupId(id);
        return list;
    }
    @RequestMapping("findById")
    public Result findById(Integer id){
        TravelGroup travelGroup = travelGroupService.findById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
    }
    @RequestMapping("/edit")
    public Result edit(Integer[] travelItemIds,@RequestBody TravelGroup travelGroup){
        travelGroupService.edit(travelItemIds,travelGroup);
        return new Result(true,MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
    }
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            travelGroupService.delete(id);
            return new Result(true,MessageConstant.DELETE_TRAVELGROUP_SUCCESS);
        } catch (RuntimeException e) {
            return new Result(false,e.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_TRAVELGROUP_FAIL);
        }
    }
    @RequestMapping("/findAll")
    public Result findAll(){
        List<TravelGroup> lists=travelGroupService.findAll();
        if(lists!=null&&lists.size()>0){
            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,lists);
        }else{
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }
}
