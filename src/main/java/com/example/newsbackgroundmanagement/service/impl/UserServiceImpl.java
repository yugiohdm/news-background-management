package com.example.newsbackgroundmanagement.service.impl;

import com.example.newsbackgroundmanagement.entity.User;
import com.example.newsbackgroundmanagement.mapper.UserMapper;
import com.example.newsbackgroundmanagement.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 邓旭波
 * @since 2023-03-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
