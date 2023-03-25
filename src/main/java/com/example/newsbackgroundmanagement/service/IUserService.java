package com.example.newsbackgroundmanagement.service;

import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 邓旭波
 * @since 2023-03-04
 */
public interface IUserService extends IService<User> {
    public BaseModel UsernameLogin(User user);

    public BaseModel PhoneLogin(User user);

    public BaseModel EmailLogin(User user);

    public BaseModel Register(User user);
}
