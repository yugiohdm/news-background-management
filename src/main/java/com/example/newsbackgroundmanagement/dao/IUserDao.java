package com.example.newsbackgroundmanagement.dao;

import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.User;

public interface IUserDao {

    public BaseModel UsernameLogin(User user);

    public BaseModel PhoneLogin(User user);

    public BaseModel EmailLogin(User user);
    public BaseModel Register(User user);

    public void UserCount();

}
