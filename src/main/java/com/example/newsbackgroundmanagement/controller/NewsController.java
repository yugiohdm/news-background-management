package com.example.newsbackgroundmanagement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.News;
import com.example.newsbackgroundmanagement.entity.User;
import com.example.newsbackgroundmanagement.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 邓旭波
 * @since 2023-02-21
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    INewsService iNewsService;
    BaseModel baseModel;

    @ResponseBody
    @RequestMapping(value = "/SelectNewsByPage")//无条件分页查询新闻
    public IPage<News> SelectNewsByPage(@RequestParam Long pagecurrent, @RequestParam Long pagesize,@RequestParam String type) {
        Page page=new Page(pagecurrent,pagesize);
        //分页查询返回的对象为 IPage 类型的
        QueryWrapper<News> newsQueryWrapper=new QueryWrapper<>();
        newsQueryWrapper.eq("category",type);
        IPage<News> newsIPage = iNewsService.page(page,newsQueryWrapper);
        //IPage 对象中有很多属性，下面举例几个最常用的
        System.out.println("总记录数：" + newsIPage.getTotal());
        System.out.println("总页数：" + newsIPage.getPages());
        System.out.println("当前页面大小：" + newsIPage.getSize());
        System.out.println("当前页码：" + newsIPage.getCurrent());
        //最重要的  =>  取出查询到的数据
        System.out.println(" ===============   数据   =================");
        List<News> records = newsIPage.getRecords();
        for (News news : records) {
            System.out.println(news);
        }
        return newsIPage;
    }




    @ResponseBody
    @RequestMapping(value = "/SelectTallNews")//搜索新闻
    public BaseModel SelectTallNews(@RequestBody News news) {
        System.out.println("-----------进入NewsController的SelectTallNews!");
        BaseModel baseModel=iNewsService.SelectTallNews(news);
        return baseModel;
    }



    @ResponseBody
    @RequestMapping(value = "/SelectNewsByTitle")//根据新闻名称查询新闻
    public BaseModel SelectNewsByTitle(@RequestBody News news){
        System.out.println("-----------进入NewsController的SelectNewsByTitle!");
        System.out.println("--------------------"+news);
        QueryWrapper<News> newQueryWrapper=new QueryWrapper<>();
        newQueryWrapper.like("title",news.getTitle());
        if(iNewsService.listObjs(newQueryWrapper)!=null){
            System.out.println("------根据新闻名称查询成功");
            baseModel = new BaseModel(231, "根据新闻名称查询新闻成功!");
            baseModel.setDatas(iNewsService.list(newQueryWrapper));
        }else{
            System.out.println("------根据新闻名称查询失败");
            baseModel = new BaseModel(232, "根据新闻名称查询新闻失败!");
        }
        return baseModel;
    }


    @ResponseBody
    @RequestMapping(value = "/InsertNews")//条件插入新闻
    public BaseModel InsertNews(@RequestBody News news){
        System.out.println("-----------进入NewsController的InsertNews!");
        System.out.println("--------------------"+news);
        QueryWrapper<News> newsQueryWrapper=new QueryWrapper<>();
        newsQueryWrapper.eq("uniquekey",news.getUniquekey());

        News news1=iNewsService.getOne(newsQueryWrapper);

        if(news1 == null) {
            iNewsService.save(news);
            List<News> aa = iNewsService.list();
            if (aa != null) {
                System.out.println("------插入成功");
                baseModel = new BaseModel(1, "插入新闻成功!");
            } else {
                System.out.println("------插入失败");
                baseModel = new BaseModel(2, "插入新闻失败!");
            }
        }else {
            System.out.println("------该新闻已存在!");
            baseModel = new BaseModel(3, "新闻已存在!不要重复插入!");
        }
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/UpdateNews")//更新新闻
    public BaseModel UpdateNews(@RequestBody News news){
        System.out.println("-----------进入NewsController的UpdateNews!");
        System.out.println("--------------------"+news);
        boolean cc=iNewsService.updateById(news);
        if (cc==true){
            System.out.println("------修改成功");
            baseModel = new BaseModel(1011, "修改新闻成功!");
        }else if (cc==false){
            System.out.println("------修改失败");
            baseModel = new BaseModel(2022, "修改新闻失败!");
        }
        return baseModel;
    }


    @ResponseBody
    @RequestMapping(value = "/DeleteNews")//根据uniquekey删除新闻
    public BaseModel DeleteNews(@RequestBody News news){
        System.out.println("-----------进入NewsController的DeleteNews!");
        System.out.println("--------------------"+news);
        QueryWrapper<News> newsQueryWrapper=new QueryWrapper<>();
        newsQueryWrapper.eq("uniquekey",news.getUniquekey());
        boolean bb=iNewsService.remove(newsQueryWrapper);
        if (bb==true){
            System.out.println("------删除成功");
            baseModel = new BaseModel(101, "删除新闻成功!");
        }else if (bb==false){
            System.out.println("------删除失败");
            baseModel = new BaseModel(202, "删除新闻失败!");
        }
        return baseModel;
    }



}
