package com.example.newsbackgroundmanagement.mapper;

import com.example.newsbackgroundmanagement.entity.Collectiblenews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.newsbackgroundmanagement.entity.UserReleasenewsDto;
import org.apache.ibatis.annotations.Select;

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
   UserReleasenewsDto getUserandReleasenews(Integer id1, Integer id2);

}
