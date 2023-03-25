package com.example.newsbackgroundmanagement.service.impl;

import com.example.newsbackgroundmanagement.dao.INewsDao;
import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.News;
import com.example.newsbackgroundmanagement.mapper.NewsMapper;
import com.example.newsbackgroundmanagement.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 邓旭波
 * @since 2023-02-21
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {
    @Autowired
    INewsDao iNewsDao;

    @Override
    public BaseModel SelectTallNews(News news) {
        System.out.println("进入------------NewsServiceImpl的SelectTallNews!");
        BaseModel baseModel=iNewsDao.SelectTallNews(news);
        return baseModel;
    }
}
