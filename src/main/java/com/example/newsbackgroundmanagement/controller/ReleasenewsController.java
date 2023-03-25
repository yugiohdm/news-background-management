package com.example.newsbackgroundmanagement.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.newsbackgroundmanagement.entity.*;
import com.example.newsbackgroundmanagement.service.IReleasenewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.example.newsbackgroundmanagement.util.DateUtil.getCurrentTimeYMDHMS;
import static com.example.newsbackgroundmanagement.util.DateUtil.getStringDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 邓旭波
 * @since 2023-03-05
 */
@Controller
@RequestMapping("/releasenews")
public class ReleasenewsController {
    @Autowired
    IReleasenewsService iReleasenewsService;
    BaseModel baseModel;


    @ResponseBody
    @RequestMapping(value = "/InsertReleaseNews")//插入一条用户发布的新闻
    public BaseModel InsertReleaseNews(@RequestBody Releasenews releasenews){
            System.out.println("-----------进入ReleasenewsController的InsertReleaseNews!");
            System.out.println("--------------------"+releasenews);
            releasenews.setState("待审核");
            releasenews.setReleasedate(getStringDate());
            releasenews.setLikes(0);
            releasenews.setNid(new Random().nextInt(1000000));
            System.out.println("++++++++++++++++++++++"+releasenews);
            boolean flag=iReleasenewsService.save(releasenews);
            if(flag==true){
                System.out.println("------插入成功");
                baseModel = new BaseModel(121, "插入用户发布的新闻成功!");
                baseModel.setData(releasenews);
            }else if(flag==false){
                System.out.println("------插入失败");
                baseModel = new BaseModel(122, "插入用户发布的新闻失败!");
            }
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/DeleteReleaseNewsByNid")//根据nid删除用户发布的新闻
    public BaseModel DeleteReleaseNewsByNid(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入ReleasenewsController的DeleteReleaseNewsByNid!");
        System.out.println("--------------------"+releasenews);
        QueryWrapper releasenewsQueryWrapper=new QueryWrapper();
        releasenewsQueryWrapper.eq("nid",releasenews.getNid());
        boolean flag1=iReleasenewsService.remove(releasenewsQueryWrapper);
        if (flag1==true){
            System.out.println("------删除成功");
            baseModel = new BaseModel(131, "删除用户发布的新闻成功!");
        }else if(flag1==false){
            System.out.println("------删除失败");
            baseModel = new BaseModel(132, "删除用户发布的新闻失败!");
        }
        return  baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/DeleteReleaseNewsByUid")//根据uid删除用户发布的新闻
    public BaseModel DeleteReleaseNewsByUid(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入ReleasenewsController的DeleteReleaseNewsByUid!");
        System.out.println("--------------------"+releasenews);
        QueryWrapper<Releasenews> releasenewsQueryWrapper=new QueryWrapper();
        releasenewsQueryWrapper.eq("uid",releasenews.getUid());
        boolean flag1=iReleasenewsService.remove(releasenewsQueryWrapper);
        if (flag1==true){
            System.out.println("------根据用户ID删除成功");
            baseModel = new BaseModel(131, "根据用户ID删除用户发布的新闻成功!");
        }else if(flag1==false){
            System.out.println("------根据用户ID删除失败");
            baseModel = new BaseModel(132, "根据用户ID删除用户发布的新闻失败!");
        }
        return  baseModel;
    }


    @ResponseBody
    @RequestMapping(value = "/SelectReleaseNews")//查询所有用户发布的新闻
    public BaseModel SelectReleaseNews(){
        System.out.println("-----------进入ReleasenewsController的SelectReleaseNews!");
        QueryWrapper<Releasenews> releasenewsQueryWrapper=new QueryWrapper();
        List<Releasenews> releasenews1=iReleasenewsService.list(releasenewsQueryWrapper);
        if(releasenews1!=null){
            System.out.println("------查询成功");
            baseModel = new BaseModel(251, "查询所有用户发布的新闻成功!");
            baseModel.setDatas(releasenews1);
        }else{
            System.out.println("------查询失败");
            baseModel = new BaseModel(252, "查询所有用户发布的新闻失败!");
        }
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/SelectReleaseNewsByUid")//根据uid查询用户发布的新闻
    public BaseModel SelectReleaseNewsByUid(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入ReleasenewsController的SelectReleaseNewsByUid!");
        System.out.println("--------------------"+releasenews);
        QueryWrapper<Releasenews> releasenewsQueryWrapper=new QueryWrapper();
        releasenewsQueryWrapper.eq("uid",releasenews.getUid());
        List<Releasenews> releasenews1=iReleasenewsService.list(releasenewsQueryWrapper);
        if(releasenews1!=null){
            System.out.println("------查询成功");
            baseModel = new BaseModel(141, "查询用户发布的新闻成功!");
            baseModel.setDatas(releasenews1);
        }else{
            System.out.println("------查询失败");
            baseModel = new BaseModel(142, "查询用户发布的新闻失败!");
        }
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/SelectLikeByUid")//根据uid查询处于审核成功状态的用户发布的新闻所获的赞的总数
    public BaseModel SelectLikeByUid(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入ReleasenewsController的SelectLikeByUid!");
        System.out.println("--------------------"+releasenews);
        int count=0;
        QueryWrapper<Releasenews> releasenewsQueryWrapper=new QueryWrapper();
        releasenewsQueryWrapper.eq("uid",releasenews.getUid()).eq("state","审核成功");
        List<Releasenews> releasenews1=iReleasenewsService.list(releasenewsQueryWrapper);
        if(releasenews1!=null){
            System.out.println("------查询成功");
            baseModel = new BaseModel(181, "查询处于审核成功状态的用户发布的新闻所获的赞的总数成功!");
            for(int i=0;i<releasenews1.size();i++){
                count=count+releasenews1.get(i).getLikes();
            }
            baseModel.setData(count);
        }else{
            System.out.println("------查询失败");
            baseModel = new BaseModel(182, "查询处于审核成功状态的用户发布的新闻所获的赞的总数失败!");
        }
        return baseModel;
    }



    @ResponseBody
    @RequestMapping(value = "/SelectReleaseNewsByStateByUid")//根据state和uid查询用户发布的新闻
    public BaseModel SelectReleaseNewsByStateByUid(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入ReleasenewsController的SelectReleaseNewsByStateByUid!");
        System.out.println("--------------------"+releasenews);
        QueryWrapper<Releasenews> releasenewsQueryWrapper=new QueryWrapper();
        releasenewsQueryWrapper.eq("state",releasenews.getState()).eq("uid",releasenews.getUid());
        List<Releasenews> releasenews1=iReleasenewsService.list(releasenewsQueryWrapper);
        if(releasenews1!=null){
            System.out.println("------根据状态和uid查询成功");
            baseModel = new BaseModel(151, "根据审核状态和uid查询用户发布的新闻成功!");
            baseModel.setDatas(releasenews1);
        }else{
            System.out.println("------根据状态和uid查询失败");
            baseModel = new BaseModel(152, "根据审核状态和uid查询用户发布的新闻失败!");
        }
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/SelectReleaseNewsByState")//根据state查询用户发布的新闻
    public BaseModel SelectReleaseNewsByState(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入ReleasenewsController的SelectReleaseNewsByState!");
        System.out.println("--------------------"+releasenews);
        QueryWrapper<Releasenews> releasenewsQueryWrapper=new QueryWrapper();
        releasenewsQueryWrapper.eq("state",releasenews.getState());
        List<Releasenews> releasenews1=iReleasenewsService.list(releasenewsQueryWrapper);
        if(releasenews1!=null){
            System.out.println("------根据状态查询成功");
            baseModel = new BaseModel(251, "根据审核状态查询用户发布的新闻成功!");
            baseModel.setDatas(releasenews1);
        }else{
            System.out.println("------根据状态查询失败");
            baseModel = new BaseModel(252, "根据审核状态查询用户发布的新闻失败!");
        }
        return baseModel;
    }


    @ResponseBody
    @RequestMapping(value = "/UpdateReleaseNewsStateByUid")//根据uid修改用户发布的新闻的状态
    public BaseModel UpdateReleaseNewsStateByUid(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入ReleasenewsController的UpdateReleaseNewsStateByUid!");
        System.out.println("--------------------"+releasenews);
        UpdateWrapper<Releasenews> releasenewsUpdateWrapper=new  UpdateWrapper();
        releasenewsUpdateWrapper.set("state",releasenews.getState()).eq("uid",releasenews.getUid());
        boolean g=iReleasenewsService.update(releasenewsUpdateWrapper);
        if(g==true){
            System.out.println("------根据用户ID修改状态成功");
            baseModel = new BaseModel(161, "根据用户ID修改用户发布的新闻的状态成功!");
        }else{
            System.out.println("------根据用户ID修改状态失败");
            baseModel = new BaseModel(162, "根据用户ID修改用户发布的新闻的状态失败!");
        }
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/UpdateReleaseNewsStateByNid")//根据nid修改用户发布的新闻的状态
    public BaseModel UpdateReleaseNewsStateByNid(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入ReleasenewsController的UpdateReleaseNewsStateByNid!");
        System.out.println("--------------------"+releasenews);
        UpdateWrapper<Releasenews> releasenewsUpdateWrapper=new  UpdateWrapper();
        releasenewsUpdateWrapper.set("state",releasenews.getState()).eq("nid",releasenews.getNid());
        boolean h=iReleasenewsService.update(releasenewsUpdateWrapper);
        if(h==true){
            System.out.println("------根据NID修改状态成功");
            baseModel = new BaseModel(171, "根据NID修改用户发布的新闻的状态成功!");
        }else{
            System.out.println("------根据NID修改状态失败");
            baseModel = new BaseModel(172, "根据NID修改用户发布的新闻的状态失败!");
        }
        return baseModel;
    }



    @ResponseBody
    @RequestMapping(value = "/AddReleaseNewsLikesByNid")//根据nid增加用户发布的新闻的获赞数
    public BaseModel AddReleaseNewsLikesByNid(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入ReleasenewsController的AddReleaseNewsLikesByNid!");
        System.out.println("--------------------"+releasenews);
        Releasenews releasenews1=iReleasenewsService.getById(releasenews.getNid());
        UpdateWrapper<Releasenews> releasenewsUpdateWrapper=new UpdateWrapper<>();
        releasenewsUpdateWrapper.set("likes",releasenews1.getLikes()+1).eq("nid",releasenews.getNid());
        boolean h=iReleasenewsService.update(releasenewsUpdateWrapper);
        if(h==true){
            System.out.println("------根据NID增加获赞数成功");
            baseModel = new BaseModel(191, "根据NID增加用户发布的新闻的获赞数成功!");
        }else{
            System.out.println("------根据NID增加获赞数失败");
            baseModel = new BaseModel(192, "根据NID增加用户发布的新闻的获赞数失败!");
        }
        return baseModel;
    }


    @ResponseBody
    @RequestMapping(value = "/ReduceReleaseNewsLikesByNid")//根据nid减少用户发布的新闻的获赞数
    public BaseModel ReduceReleaseNewsLikesByNid(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入ReleasenewsController的ReduceReleaseNewsLikesByNid!");
        System.out.println("--------------------"+releasenews);
        Releasenews releasenews1=iReleasenewsService.getById(releasenews.getNid());
        UpdateWrapper<Releasenews> releasenewsUpdateWrapper=new  UpdateWrapper<>();
        releasenewsUpdateWrapper.set("likes",releasenews1.getLikes()-1).eq("nid",releasenews.getNid());
        boolean h=iReleasenewsService.update(releasenewsUpdateWrapper);
        if(h==true){
            System.out.println("------根据NID减少获赞数成功");
            baseModel = new BaseModel(201, "根据NID减少用户发布的新闻的获赞数成功!");
        }else{
            System.out.println("------根据NID减少获赞数失败");
            baseModel = new BaseModel(202, "根据NID减少用户发布的新闻的获赞数失败!");
        }
        return baseModel;
    }


    @ResponseBody
    @RequestMapping(value = "/UpdateReleaseNews")//修改用户发布的新闻
    public BaseModel UpdateReleaseNews(@RequestBody Releasenews  releasenews){
        System.out.println("-----------进入ReleasenewsController的UpdateReleaseNews!");
        System.out.println("--------------------"+releasenews);
        QueryWrapper<Releasenews> releasenewsQueryWrapper=new QueryWrapper<>();
        releasenewsQueryWrapper.eq("nid",releasenews.getNid());
        if (iReleasenewsService.getOne(releasenewsQueryWrapper)!=null) {
            boolean c = iReleasenewsService.updateById(releasenews);
            if (c == true) {
                System.out.println("------修改成功");
                baseModel = new BaseModel(261, "修改用户发布的新闻成功!");
            } else if (c == false) {
                System.out.println("------修改失败");
                baseModel = new BaseModel(262, "修改用户发布的新闻失败!");
            }
        }else{
            baseModel = new BaseModel(263, "该用户发布的新闻不存在!");
        }
        return baseModel;
    }

    @ResponseBody
    @RequestMapping(value = "/DeleteReleaseNews")//根据nid删除用户发布的新闻
    public BaseModel DeleteReleaseNews(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入ReleasenewsController的DeleteReleaseNews!");
        System.out.println("--------------------"+releasenews);
        QueryWrapper<Releasenews> releasenewsQueryWrapper=new QueryWrapper<>();
        releasenewsQueryWrapper.eq("nid",releasenews.getNid());
        boolean bb=iReleasenewsService.remove(releasenewsQueryWrapper);
        if (bb==true){
            System.out.println("------删除成功");
            baseModel = new BaseModel(271, "删除用户发布的新闻成功!");
        }else if (bb==false){
            System.out.println("------删除失败");
            baseModel = new BaseModel(272, "删除用户发布的新闻失败!");
        }
        return baseModel;
    }


    @ResponseBody
    @RequestMapping(value = "/upload")
    public  BaseModel upload(@RequestParam("uploadFile") MultipartFile file, @RequestParam("name") String name){
        System.out.println("名字========"+name);
        BaseModel baseModel=new BaseModel(0,"0");
        String filePath=uploadFile(file);
        if(!StringUtils.isEmpty(filePath)){
            System.out.println("上传成功--------------");
            baseModel.setCode(300);
            baseModel.setMessage("上传成功!");
            baseModel.setData(filePath);
        }else{
            System.out.println("上传失败--------------");
            baseModel.setCode(301);
            baseModel.setMessage("上传失败!");
        }
        return  baseModel;
    }

    public  String uploadFile(MultipartFile file){
        System.out.println("进入--------------uploadFile!");
        //重新定义文件名，避免名称重复，全局唯一标识符，是指在一台机器上生成的数字，它保证对在统一时空中的所有机器都是唯一的，是由一个十六进制的数字组成，表现出来的形式
        String fileName= UUID.randomUUID().toString()
                .replace("-","").substring(0,15)+"_file_"+file.getOriginalFilename();
        System.out.println("进入--------------"+fileName);
        //上传文件至指定位置
        String disk="E:";
        String dicr="news";
        //File.separator 是系统默认的文件分隔符号
        File newFlieDic=new File(disk+ File.separator+dicr);
        if(!newFlieDic.exists()){
            //用于创建多级目录
            newFlieDic.mkdirs();
        }
        File newfile=new File(newFlieDic.getPath(),fileName);
        //将file移动到targetfile目录下
        try {
            file.transferTo(newfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("----------------"+dicr+"/"+fileName);
        return fileName;
    }
}
