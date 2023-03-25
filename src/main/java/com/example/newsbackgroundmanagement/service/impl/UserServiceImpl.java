package com.example.newsbackgroundmanagement.service.impl;

import com.example.newsbackgroundmanagement.dao.IUserDao;
import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.User;
import com.example.newsbackgroundmanagement.mapper.UserMapper;
import com.example.newsbackgroundmanagement.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    //注入数据访问层 UserMapper 对象
    @Autowired
    IUserDao iUserDao;



    @Override
    public BaseModel UsernameLogin(User user) {
        System.out.println("-----------进入UserServiceImpl的UsernameLogin!");
        BaseModel baseModel=iUserDao.UsernameLogin(user);
        return baseModel;
    }

    @Override
    public BaseModel PhoneLogin(User user) {
        System.out.println("-----------进入UserServiceImpl的PhoneLogin!");
        BaseModel baseModel=iUserDao.PhoneLogin(user);
        return baseModel;
    }

    @Override
    public BaseModel EmailLogin(User user) {
        System.out.println("-----------进入UserServiceImpl的EmailLogin!");
        BaseModel baseModel=iUserDao.EmailLogin(user);
        return baseModel;
    }

    @Override
    public BaseModel Register(User user) {
        System.out.println("-----------进入UserServiceImpl的Register!");
        System.out.println("--------------------"+user);
        System.out.println("------用户名="+user.getUsername()+"------密码="+user.getPassword());
        BaseModel baseModel=iUserDao.Register(user);
        return baseModel;
    }
}
