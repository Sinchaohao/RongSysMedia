package com.sixin.broad.domain;

import com.sixin.common.base.BaseEntity;

/**
 * Created by ASUS on 2019/8/19.
 *
 * @description
 * @author  cx
 */
public class HigherAnn extends BaseEntity {

    /*公告标题*/
    private String title;

    /*公告内容*/
    private String content;

    /*发送人*/
    private String uname;

    /*发送时间*/
    private String sendtime;

    /*是否已读*/
    private String isread;

    /*上级公告列表信息编号*/
    private int mhid;

    /*所属分组区域*/
    private String aid;

    /*发送用户编号*/
    private String senduserid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public int getMhid() {
        return mhid;
    }

    public void setMhid(int mhid) {
        this.mhid = mhid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(String senduserid) {
        this.senduserid = senduserid;
    }

    @Override
    public String toString() {
        return "HigherAnn{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", uname='" + uname + '\'' +
                ", sendtime='" + sendtime + '\'' +
                ", isread='" + isread + '\'' +
                ", mhid=" + mhid +
                ", aid='" + aid + '\'' +
                ", senduserid='" + senduserid + '\'' +
                '}';
    }
}
