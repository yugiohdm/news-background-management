package com.example.newsbackgroundmanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class UserReleasenewsDto extends User{
    private static final long serialVersionUID = 1L;

    @TableId(value = "nid", type = IdType.AUTO)
    private Integer nid;
    private String newstype;

    private Integer likes;

    private String newscontent;

    private String releasedate;

    private String newstitle;

    private String newimageurl;

    private String state;



    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewimageurl() {
        return newimageurl;
    }

    public void setNewimageurl(String newimageurl) {
        this.newimageurl = newimageurl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "Releasenews{" +
                "nid=" + nid +
                ", newstype=" + newstype +
                ", likes=" + likes +
                ", newscontent=" + newscontent +
                ", releasedate=" + releasedate +
                ", newstitle=" + newstitle +
                ", newimageurl=" + newimageurl +
                ", state=" + state +
                "}";
    }


}
