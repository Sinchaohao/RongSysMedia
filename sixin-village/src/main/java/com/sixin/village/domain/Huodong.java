package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;

/**
 * @Description: 党员活动表$
 * @Param: $
 * @return: $
 * @Author: Mr.DYQ
 * @Date: $
 */
public class Huodong extends BaseEntity{
    private static final long serialVersionUID = 1L;
    /**活动编号*/
    private String hdid;
    /**活动标题*/
    private String hdtitle;
    /**活动类型*/
    private String hdtype;
    /**活动地点*/
    private String hdloc;
    /**活动人数*/
    private String hdnum;
    /**活动开始日期*/
    private String hdbtime;
    /**活动结束日期*/
    private String hdetime;
    /***活动图片*/
    private String hdpic;
    /**活动简介*/
    private String hdsum;
    /**活动添加日期*/
    private String hdadddate;
    /**添加人编号*/
    private int uid;
    /**添加人姓名*/
    private String uname;
    /**用户所属地区村社区 aid*/
    private String aid;
    /**是否有效*/
    private String remark;

    @Override
    public String toString() {
        return "Huodong{" +
                "hdid='" + hdid + '\'' +
                ", hdtitle='" + hdtitle + '\'' +
                ", hdtype='" + hdtype + '\'' +
                ", hdloc='" + hdloc + '\'' +
                ", hdnum='" + hdnum + '\'' +
                ", hdbtime='" + hdbtime + '\'' +
                ", hdetime='" + hdetime + '\'' +
                ", hdpic='" + hdpic + '\'' +
                ", hdsum='" + hdsum + '\'' +
                ", hdadddate='" + hdadddate + '\'' +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", aid='" + aid + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Huodong(String hdid, String hdtitle, String hdtype, String hdloc,
                   String hdnum, String hdbtime,
                   String hdetime, String hdpic,
                   String hdsum, String hdadddate
            , int uid, String uname, String aid, String remark) {
        this.hdid = hdid;
        this.hdtitle = hdtitle;
        this.hdtype = hdtype;
        this.hdloc = hdloc;
        this.hdnum = hdnum;
        this.hdbtime = hdbtime;
        this.hdetime = hdetime;
        this.hdpic = hdpic;
        this.hdsum = hdsum;
        this.hdadddate = hdadddate;
        this.uid = uid;
        this.uname = uname;
        this.aid = aid;
        this.remark = remark;
    }
    public Huodong() {
        super();}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getHdid() {
        return hdid;
    }

    public void setHdid(String hdid) {
        this.hdid = hdid;
    }

    public String getHdtitle() {
        return hdtitle;
    }

    public void setHdtitle(String hdtitle) {
        this.hdtitle = hdtitle;
    }

    public String getHdtype() {
        return hdtype;
    }

    public void setHdtype(String hdtype) {
        this.hdtype = hdtype;
    }

    public String getHdloc() {
        return hdloc;
    }

    public void setHdloc(String hdloc) {
        this.hdloc = hdloc;
    }

    public String getHdnum() {
        return hdnum;
    }

    public void setHdnum(String hdnum) {
        this.hdnum = hdnum;
    }

    public String getHdbtime() {
        return hdbtime;
    }

    public void setHdbtime(String hdbtime) {
        this.hdbtime = hdbtime;
    }

    public String getHdetime() {
        return hdetime;
    }

    public void setHdetime(String hdetime) {
        this.hdetime = hdetime;
    }

    public String getHdpic() {
        return hdpic;
    }

    public void setHdpic(String hdpic) {
        this.hdpic = hdpic;
    }

    public String getHdsum() {
        return hdsum;
    }

    public void setHdsum(String hdsum) {
        this.hdsum = hdsum;
    }

    public String getHdadddate() {
        return hdadddate;
    }

    public void setHdadddate(String hdadddate) {
        this.hdadddate = hdadddate;
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

