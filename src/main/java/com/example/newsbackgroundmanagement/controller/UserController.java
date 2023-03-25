package com.example.newsbackgroundmanagement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.User;
import com.example.newsbackgroundmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 邓旭波
 * @since 2023-03-04
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;

    BaseModel baseModel;

    @ResponseBody
    @RequestMapping(value = "/SelectUserByPage")//无条件分页查询用户
    public IPage<User> SelectUserByPage(@RequestParam Long pagecurrent, @RequestParam Long pagesize) {
        Page page=new Page(pagecurrent,pagesize);
        //分页查询返回的对象为 IPage 类型的
        IPage<User> userIPage = iUserService.page(page, null);
        //IPage 对象中有很多属性，下面举例几个最常用的
        System.out.println("总记录数：" + userIPage.getTotal());
        System.out.println("总页数：" + userIPage.getPages());
        System.out.println("当前页面大小：" + userIPage.getSize());
        System.out.println("当前页码：" + userIPage.getCurrent());
        //最重要的  =>  取出查询到的数据
        System.out.println(" ===============   数据   =================");
        List<User> records = userIPage.getRecords();
        for (User user : records) {
            System.out.println(user);
        }
        return userIPage;
    }

    @ResponseBody
    @RequestMapping(value = "/SelectUserByType")//根据类型查询用户
    public BaseModel SelectUserByType(@RequestBody User user){
        System.out.println("-----------进入UserController的SelectUserByType!");
        System.out.println("--------------------"+user);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("type",user.getType());
        if(iUserService.listObjs(userQueryWrapper)!=null){
            System.out.println("------根据类型查询成功");
            baseModel = new BaseModel(1110, "根据类型查询用户成功!");
            baseModel.setDatas(iUserService.list(userQueryWrapper));
        }else{
            System.out.println("------根据类型查询失败");
            baseModel = new BaseModel(2220, "根据类型查询用户失败!");
        }
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/SelectUserByUsername")//根据用户名查询普通用户
    public BaseModel SelectUserByUsername(@RequestBody User user){
        System.out.println("-----------进入UserController的SelectUserByUsername!");
        System.out.println("--------------------"+user);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.like("username",user.getUsername()).eq("type","普通用户");
        if(iUserService.listObjs(userQueryWrapper)!=null){
            System.out.println("------根据用户名查询成功");
            baseModel = new BaseModel(171, "根据用户名查询普通用户成功!");
            baseModel.setDatas(iUserService.list(userQueryWrapper));
        }else{
            System.out.println("------根据用户名查询失败");
            baseModel = new BaseModel(172, "根据用户名查询普通用户失败!");
        }
        return baseModel;
    }


    @ResponseBody
    @RequestMapping(value = "/UsernameLogin")//用户名登录
    public BaseModel UsernameLogin(@RequestBody User user){
        System.out.println("-----------进入UserController的UsernameLogin!");
        System.out.println("--------------------"+user);
        BaseModel baseModel=iUserService.UsernameLogin(user);
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/PhoneLogin")//手机号登录
    public BaseModel PhoneLogin(@RequestBody User user){
        System.out.println("-----------进入UserController的PhoneLogin!");
        System.out.println("--------------------"+user);
        BaseModel baseModel=iUserService.PhoneLogin(user);
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/EmailLogin")//邮箱账号登录
    public BaseModel EmailLogin(@RequestBody User user){
        System.out.println("-----------进入UserController的EmailLogin!");
        System.out.println("--------------------"+user);
        BaseModel baseModel=iUserService.EmailLogin(user);
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/Register")//注册
    public BaseModel Register(@RequestBody User user){
        System.out.println("-----------进入UserController的Register!");
        System.out.println("--------------------"+user);
        BaseModel baseModel=iUserService.Register(user);
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/UpdatePassword")//更改密码
    public BaseModel UpdatePassword(@RequestBody User user){
        System.out.println("-----------进入UserController的UpdatePassword!");
        System.out.println("--------------------"+user);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername()).or().eq("phone",user.getPhone()).or().eq("email",user.getEmail());
        if(iUserService.getOne(userQueryWrapper)!=null) {
            UpdateWrapper<User> userUpdateWrapper=new UpdateWrapper<>();
            userUpdateWrapper.set("password",user.getPassword()).eq("username",user.getUsername()).or().eq("phone",user.getPhone()).or().eq("email",user.getEmail());
            boolean b=iUserService.update(userUpdateWrapper);
            if(b==true){
                System.out.println("------修改成功");
                baseModel = new BaseModel(111, "修改密码成功!");
                baseModel.setData(user.getPassword());
            }else if(b==false){
                System.out.println("------修改失败");
                baseModel = new BaseModel(222, "修改密码失败!");

            }
        }else{
            baseModel = new BaseModel(333, "该用户不存在!");
        }
        return baseModel;
    }



    @ResponseBody
    @RequestMapping(value = "/SelectPassword")//查找密码
    public BaseModel SelectPassword(@RequestBody User user){
        System.out.println("-----------进入UserController的SelectPassword!");
        System.out.println("--------------------"+user);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername()).or().eq("phone",user.getPhone()).or().eq("email",user.getEmail());;
        if(iUserService.getOne(userQueryWrapper)!=null) {
            User user1=iUserService.getOne(userQueryWrapper);
            System.out.println("------查找成功");
            baseModel = new BaseModel(141, "查找密码成功!");
            baseModel.setData(user1.getPassword());
        }else{
            System.out.println("------查找失败");
            baseModel = new BaseModel(142, "查找密码失败!");
        }
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/UpdateUserSex")//修改用户性别
    public BaseModel UpdateUserSex(@RequestBody User user){
        System.out.println("-----------进入UserController的UpdateUserSex!");
        System.out.println("--------------------"+user);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        if(iUserService.getOne(userQueryWrapper)!=null) {
            UpdateWrapper<User> userUpdateWrapper=new UpdateWrapper<>();
            userUpdateWrapper.set("sex",user.getSex()).eq("username",user.getUsername());
            boolean c=iUserService.update(userUpdateWrapper);
            if(c==true){
                System.out.println("------修改用户性别成功");
                baseModel = new BaseModel(101, "修改用户性别信息成功!");
            }else if(c==false){
                System.out.println("------修改用户性别失败");
                baseModel = new BaseModel(102, "修改用户性别信息失败!");

            }
        }else{
            baseModel = new BaseModel(103, "该用户不存在!");
        }
        return baseModel;
    }



    @ResponseBody
    @RequestMapping(value = "/UpdateUserBirthday")//修改用户生日
    public BaseModel UpdateUserBirthday(@RequestBody User user){
        System.out.println("-----------进入UserController的UpdateUserBirthday!");
        System.out.println("--------------------"+user);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        if(iUserService.getOne(userQueryWrapper)!=null) {
            UpdateWrapper<User> userUpdateWrapper=new UpdateWrapper<>();
            userUpdateWrapper.set("birthday",user.getBirthday()).eq("username",user.getUsername());
            boolean d=iUserService.update(userUpdateWrapper);
            if(d==true){
                System.out.println("------修改用户生日成功");
                baseModel = new BaseModel(201, "修改用户生日信息成功!");
            }else if(d==false){
                System.out.println("------修改用户生日失败");
                baseModel = new BaseModel(202, "修改用户生日信息失败!");

            }
        }else{
            baseModel = new BaseModel(203, "该用户不存在!");
        }
        return baseModel;
    }


    @ResponseBody
    @RequestMapping(value = "/UpdateUserLocation")//修改用户所在地
    public BaseModel UpdateUserLocation(@RequestBody User user){
        System.out.println("-----------进入UserController的UpdateUserLocation!");
        System.out.println("--------------------"+user);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        if(iUserService.getOne(userQueryWrapper)!=null) {
            UpdateWrapper<User> userUpdateWrapper=new UpdateWrapper<>();
            userUpdateWrapper.set("location",user.getLocation()).eq("username",user.getUsername());
            boolean e=iUserService.update(userUpdateWrapper);
            if(e==true){
                System.out.println("------修改用户所在地成功");
                baseModel = new BaseModel(301, "修改用户所在地信息成功!");
            }else if(e==false){
                System.out.println("------修改用户所在地失败");
                baseModel = new BaseModel(302, "修改用户所在地信息失败!");

            }
        }else{
            baseModel = new BaseModel(303, "该用户不存在!");
        }
        return baseModel;
    }


    @ResponseBody
    @RequestMapping(value = "/UpdateUserOccupation")//修改用户职业
    public BaseModel UpdateUserOccupation(@RequestBody User user){
        System.out.println("-----------进入UserController的UpdateUserOccupation!");
        System.out.println("--------------------"+user);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        if(iUserService.getOne(userQueryWrapper)!=null) {
            UpdateWrapper<User> userUpdateWrapper=new UpdateWrapper<>();
            userUpdateWrapper.set("occupation",user.getOccupation()).eq("username",user.getUsername());
            boolean f=iUserService.update(userUpdateWrapper);
            if(f==true){
                System.out.println("------修改用户职业成功");
                baseModel = new BaseModel(401, "修改用户职业信息成功!");
            }else if(f==false){
                System.out.println("------修改用户职业失败");
                baseModel = new BaseModel(402, "修改用户职业信息失败!");

            }
        }else{
            baseModel = new BaseModel(403, "该用户不存在!");
        }
        return baseModel;
    }


    @ResponseBody
    @RequestMapping(value = "/UpdateUser")//修改用户
    public BaseModel UpdateUser(@RequestBody User user){
        System.out.println("-----------进入UserController的UpdateUser!");
        System.out.println("--------------------"+user);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("uid",user.getUid());
        if (iUserService.getOne(userQueryWrapper)!=null) {
            boolean c = iUserService.updateById(user);
            if (c == true) {
                System.out.println("------修改成功");
                baseModel = new BaseModel(10, "修改用户成功!");
            } else if (c == false) {
                System.out.println("------修改失败");
                baseModel = new BaseModel(20, "修改用户失败!");
            }
        }else{
            baseModel = new BaseModel(30, "该用户不存在!");
        }
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/DeleteUser")//删除用户
    public BaseModel DeleteUser(@RequestBody User user){
        System.out.println("-----------进入UserController的DeleteUser!");
        System.out.println("--------------------"+user);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("uid",user.getUid()).or().eq("username",user.getUsername()).or().eq("phone",user.getPhone()).or().eq("email",user.getEmail());
        if(iUserService.getOne(userQueryWrapper)!=null){
            boolean d=iUserService.remove(userQueryWrapper);
            if(d==true){
                System.out.println("------删除成功");
                baseModel = new BaseModel(110, "删除用户成功!");
            }else if(d==false){
                System.out.println("------删除失败");
                baseModel = new BaseModel(220, "删除用户失败!");
            }
        }else{
            baseModel = new BaseModel(330, "该用户不存在!");
        }
        return  baseModel;
    }
}
