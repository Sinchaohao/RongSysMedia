package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;

/**
 * @program: lyb_RongSys
 * @description: 投诉咨询表
 * @author: Mr.Liu
 * @create: 2019-01-14 15:25
 **/
public class Complaint extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**投诉数据编号*/
    private String fbid;
    /**投诉人编号*/
    private String fbuid;
    /** 投诉标题*/
    private String title;
    /** 投诉类型*/
    private String ftype;
    /** 投诉内容*/
    private String content;
    /** 投诉时间*/
    private String fbdatetime;
    /** 管理员是否已读*/
    private int isread;
    /** 回复内容*/
    private String opinion;
    /** 回复人编号*/
    private int uid;
    /** 回复人用户名*/
    private String uname;
    /** 回复时间*/
    private String datetime;
    /** 用户所属地区村社区 aid*/
    private String aid;
    /** 是否有效*/
    private String remark;

    @Override
    public String toString() {
        return "Complaint{" +
                "fbid='" + fbid + '\'' +
                ", fbuid='" + fbuid + '\'' +
                ", title='" + title + '\'' +
                ", ftype='" + ftype + '\'' +
                ", content='" + content + '\'' +
                ", fbdatetime='" + fbdatetime + '\'' +
                ", isread=" + isread +
                ", opinion='" + opinion + '\'' +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", datetime='" + datetime + '\'' +
                ", aid='" + aid + '\'' +
                ", remark=" + remark +
                '}';
    }

    public Complaint(String fbid, String fbuid, String title,
                     String ftype, String content, String fbdatetime,
                     int isread, String opinion, int uid, String uname,
                     String datetime, String remark, String aid) {
        this.fbid = fbid;
        this.fbuid = fbuid;
        this.title = title;
        this.ftype = ftype;
        this.content = content;
        this.fbdatetime = fbdatetime;
        this.isread = isread;
        this.opinion = opinion;
        this.uid = uid;
        this.uname = uname;
        this.datetime = datetime;
        this.remark = remark;
        this.aid = aid;
    }

    public Complaint() {
        super();
    }


    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getFbuid() {
        return fbuid;
    }

    public void setFbuid(String fbuid) {
        this.fbuid = fbuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFbdatetime() {
        return fbdatetime;
    }

    public void setFbdatetime(String fbdatetime) {
        this.fbdatetime = fbdatetime;
    }

    public int getIsread() {
        return isread;
    }

    public void setIsread(int isread) {
        this.isread = isread;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }
}
