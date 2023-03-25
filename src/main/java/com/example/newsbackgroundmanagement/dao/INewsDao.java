package com.example.newsbackgroundmanagement.dao;

import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.News;

public interface INewsDao {

    public BaseModel SelectTallNews(News news);
}
