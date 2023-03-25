package com.example.newsbackgroundmanagement.service;

import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 邓旭波
 * @since 2023-02-21
 */
public interface INewsService extends IService<News> {
    public BaseModel SelectTallNews(News news);
}
