package com.example.newsbackgroundmanagement.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.News;
import com.example.newsbackgroundmanagement.mapper.NewsMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsDao implements INewsDao{

    @Resource
    NewsMapper newsMapper;

    BaseModel baseModel;



    @Override
    public BaseModel SelectTallNews(News news) {
        System.out.println("进入------------NewsDao的SelectTallNews!");
        System.out.println("-----------news"+news);
        List<News> newsList=new ArrayList<>();

        if(news==null||news.getTitle()==""||news.getTitle()==null){
            System.out.println("进入------------if！");
            QueryWrapper<News> newsQueryWrapper=new QueryWrapper<>();
            newsList=newsMapper.selectList(newsQueryWrapper);
            for(News dd:newsList){
                System.out.println("------"+dd.getTitle());
            }
            baseModel=new BaseModel(100,"数据返回成功！");
            baseModel.setDatas(newsList);

        }else{
            System.out.println("进入------------按Carmodel查询！");
            System.out.println("------------car.getCarmodel()="+news.getTitle());
            QueryWrapper<News> newsQueryWrapper=new QueryWrapper<>();
            newsQueryWrapper.like("title",news.getTitle());
            newsList=newsMapper.selectList(newsQueryWrapper);

            if(newsList.isEmpty()){
                System.out.println("进入------------newsList.isEmpty()为空！");
                baseModel=new BaseModel(200,"返回数据为空！");
            }else {
                System.out.println("进入------------ll.isEmpty()不为空！");
                for(News dd:newsList){
                    System.out.println("------"+dd.getTitle());
                }
                baseModel=new BaseModel(100,"数据返回成功！");
                baseModel.setDatas(newsList);
            }
        }
        return baseModel;
    }
}
