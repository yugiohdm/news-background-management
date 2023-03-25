package com.example.newsbackgroundmanagement.controller;

import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.Collectiblenews;
import com.example.newsbackgroundmanagement.entity.UserReleasenewsDto;
import com.example.newsbackgroundmanagement.mapper.CollectiblenewsMapper;
import com.example.newsbackgroundmanagement.service.ICollectiblenewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 邓旭波
 * @since 2023-02-28
 */
@Controller
@RequestMapping("/collectiblenews")
public class CollectiblenewsController {
    @Autowired
    ICollectiblenewsService iCollectiblenewsService;

    @Resource
    CollectiblenewsMapper collectiblenewsMapper;
    BaseModel baseModel;


    @ResponseBody
    @RequestMapping(value = "/getUserandReleasenews")//分别查询用户信息和用户发布的新闻信息
    public BaseModel getUserandReleasenews(@RequestBody Collectiblenews collectiblenews){
        System.out.println("-----------进入CollectiblenewsController的getUserandReleasenews!");
        UserReleasenewsDto userReleasenewsDto=collectiblenewsMapper.getUserandReleasenews(collectiblenews.getUid(),collectiblenews.getNid());
       if(userReleasenewsDto!=null){
           baseModel=new BaseModel(0,"查询数据成功!");
           baseModel.setData(userReleasenewsDto);
       }else{
           baseModel=new BaseModel(-1,"查询数据失败!");
       }
        return baseModel;
    }



}
