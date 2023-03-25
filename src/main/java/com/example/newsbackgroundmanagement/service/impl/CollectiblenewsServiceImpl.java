package com.example.newsbackgroundmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.Collectiblenews;
import com.example.newsbackgroundmanagement.entity.Releasenews;
import com.example.newsbackgroundmanagement.entity.User;
import com.example.newsbackgroundmanagement.mapper.CollectiblenewsMapper;
import com.example.newsbackgroundmanagement.mapper.ReleasenewsMapper;
import com.example.newsbackgroundmanagement.mapper.UserMapper;
import com.example.newsbackgroundmanagement.service.ICollectiblenewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 邓旭波
 * @since 2023-02-28
 */
@Service
public class CollectiblenewsServiceImpl extends ServiceImpl<CollectiblenewsMapper, Collectiblenews> implements ICollectiblenewsService {

}
