package com.example.newsbackgroundmanagement.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.User;
import com.example.newsbackgroundmanagement.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao implements IUserDao{

    @Resource
    UserMapper userMapper;

    BaseModel baseModel;

    @Override
    public BaseModel UsernameLogin(User user) {
        System.out.println("--------------UserDao的UsernameLogin!");
        System.out.println("---------------用户名:"+user.getUsername()+"----密码:"+user.getPassword());
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername()).eq("password",user.getPassword());
        User user1=userMapper.selectOne(userQueryWrapper);
        System.out.println(user);
        if(user1==null){
            System.out.println("------------user1=null");
            baseModel=new BaseModel(100,"登录失败!");
        }else{
            System.out.println("------------user1="+user1.getUsername());
            baseModel=new BaseModel(200,"登录成功!");
            baseModel.setData(user1);
        }
        return baseModel;
    }

    @Override
    public BaseModel PhoneLogin(User user) {
        System.out.println("--------------UserDao的PhoneLogin!");
        System.out.println("---------------手机号:"+user.getPhone()+"----密码:"+user.getPassword());
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("phone",user.getPhone()).eq("password",user.getPassword());
        User user2=userMapper.selectOne(userQueryWrapper);
        System.out.println(user);
        if(user2==null){
            System.out.println("------------user2=null");
            baseModel=new BaseModel(1000,"登录失败!");
        }else{
            System.out.println("------------user2="+user2.getPhone());
            baseModel=new BaseModel(2000,"登录成功!");
            baseModel.setData(user2);
        }
        return baseModel;
    }

    @Override
    public BaseModel EmailLogin(User user) {
        System.out.println("--------------UserDao的EmailLogin!");
        System.out.println("---------------邮箱账号:"+user.getPhone()+"----密码:"+user.getPassword());
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("email",user.getEmail()).eq("password",user.getPassword());
        User user3=userMapper.selectOne(userQueryWrapper);
        System.out.println(user);
        if(user3==null){
            System.out.println("------------user3=null");
            baseModel=new BaseModel(10000,"登录失败!");
        }else{
            System.out.println("------------user3="+user3.getEmail());
            baseModel=new BaseModel(20000,"登录成功!");
            baseModel.setData(user3);
        }
        return baseModel;
    }

    @Override
    public BaseModel Register(User user) {
        System.out.println("----------进入UserDao的Register!");
        System.out.println("--------------------"+user);
        System.out.println("----------用户名="+user.getUsername()+"-----密码="+user.getPassword());
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        if (userMapper.selectOne(userQueryWrapper)==null) {
            int a=userMapper.insert(user);
            if (a == 1) {
                System.out.println("------插入成功");
                baseModel = new BaseModel(22, "注册成功!");
            } else if (a == 0) {
                System.out.println("------插入失败");
                baseModel = new BaseModel(11, "注册失败!");
            }
        }else{
            baseModel = new BaseModel(33, "已存在该用户名!");
        }
        return baseModel;
    }



    @Override
    public void UserCount() {
        Long count=userMapper.selectCount(null);
    }



}
