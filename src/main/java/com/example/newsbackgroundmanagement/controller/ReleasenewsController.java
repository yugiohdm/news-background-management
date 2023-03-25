package com.example.newsbackgroundmanagement.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.newsbackgroundmanagement.entity.BaseModel;
import com.example.newsbackgroundmanagement.entity.News;
import com.example.newsbackgroundmanagement.entity.Releasenews;
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
            System.out.println("-----------进入releasenews的InsertReleaseNews!");
            System.out.println("--------------------"+releasenews);
            releasenews.setState("未审核");
            releasenews.setReleasedate(String.valueOf(new Date()));
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
    @RequestMapping(value = "/DeleteReleaseNews")//根据nid删除用户发布的新闻
    public BaseModel DeleteReleaseNews(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入releasenews的DeleteReleaseNews!");
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
    @RequestMapping(value = "/SelectReleaseNewsByNid")//根据uid查询用户发布的新闻
    public BaseModel SelectReleaseNewsByNid(@RequestBody Releasenews releasenews){
        System.out.println("-----------进入releasenews的SelectReleaseNewsByNid!");
        System.out.println("--------------------"+releasenews);
        QueryWrapper releasenewsQueryWrapper=new QueryWrapper();
        releasenewsQueryWrapper.eq("uid",releasenews.getUid());
        List<Releasenews> releasenews1=iReleasenewsService.list(releasenewsQueryWrapper);
        if(releasenews1!=null){
            System.out.println("------查询成功");
            baseModel = new BaseModel(141, "查询用户发布的新闻成功!");
            baseModel.setDatas(releasenews1);
        }else{
            System.out.println("------查询成功");
            baseModel = new BaseModel(142, "查询用户发布的新闻成功!");
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
