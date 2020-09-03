package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;

/**
 * @program: dyq_RongSys
 * @description: 党员在线学习表
 * @author: Mr.dyq
 * @create: 2019-01-14 15:25
 **/
public class Education extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**在线学习课程编号*/
    private String eid;
    /**课程名称*/
    private String courtit;
    /** 课程介绍*/
    private String coursum;
    /** 课程地址*/
    private String courass;
    /** 添加时间*/
    private String adddate;
    /** 回复人编号*/
    private int uid;
    /** 回复人用户名*/
    private String uname;
    /** 浏览数*/
    private String courread;
    /** 缩略图*/
    private String courpic;
    /** 用户所属地区村社区 aid*/
    private String aid;
    /** 是否有效*/
    private String remark;

    @Override
    public String toString() {
        return "Education{" +
                "eid='" + eid + '\'' +
                ", courtit='" + courtit + '\'' +
                ", coursum='" + coursum + '\'' +
                ", courass='" + courass + '\'' +
                ", adddate='" + adddate + '\'' +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", courread='" + courread + '\'' +
                ", courpic='" + courpic + '\'' +
                ", aid='" + aid + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Education(String eid, String courtit,
                     String coursum, String courass,
                     String adddate, int uid, String uname,
                     String courread, String courpic, String aid, String remark) {
        this.eid = eid;
        this.courtit = courtit;
        this.coursum = coursum;
        this.courass = courass;
        this.adddate = adddate;
        this.uid = uid;
        this.uname = uname;
        this.courread = courread;
        this.courpic = courpic;
        this.aid = aid;
        this.remark = remark;
    }
    public Education() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getCourtit() {
        return courtit;
    }

    public void setCourtit(String courtit) {
        this.courtit = courtit;
    }

    public String getCoursum() {
        return coursum;
    }

    public void setCoursum(String coursun) {
        this.coursum = coursum;
    }

    public String getCourass() {
        return courass;
    }

    public void setCourass(String courass) {
        this.courass = courass;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
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

    public String getCourread() {
        return courread;
    }

    public void setCourread(String courread) {
        this.courread = courread;
    }

    public String getCourpic() {
        return courpic;
    }

    public void setCourpic(String courpic) {
        this.courpic = courpic;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
