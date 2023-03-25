package com.example.newsbackgroundmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 邓旭波
 * @since 2023-02-28
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collectiblenews implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer uid;

    private String username;

    private Integer nid;

    private String newstitle;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    @Override
    public String toString() {
        return "Collectiblenews{" +
        "uid=" + uid +
        ", username=" + username +
        ", nid=" + nid +
        ", newstitle=" + newstitle +
        "}";
    }
}
