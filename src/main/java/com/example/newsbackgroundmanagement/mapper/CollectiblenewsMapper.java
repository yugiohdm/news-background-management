package com.example.newsbackgroundmanagement.mapper;

import com.example.newsbackgroundmanagement.entity.Collectiblenews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 邓旭波
 * @since 2023-02-28
 */
public interface CollectiblenewsMapper extends BaseMapper<Collectiblenews> {
    @Select("select r.*,u.*  from  user u,releasenews r where u.uid=#{id1} and r.nid=#{id2}")
    Collectiblenews getUserandReleasenews(Integer id1,Integer id2);

}
