package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;

import java.util.List;

/**
 * @program: lyb_RongSys
 * @description: 暮云圈 留言表
 * @author: Mr.Liu
 * @create: 2019-01-17 15:23
 **/
public class Socialcircle extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**暮云圈编号*/
    private int pcid;
    /**评论数目*/
    private int pcsennum;
    /**用户所属地区村社区 aid*/
    private String aid;
    /**上传视频地址*/
    private String videourl;
    /**上传图片地址*/
    private String imgurl;
    /**发布时间*/
    private String adddate;
    /**发布内容*/
    private String pcontent;
    /**发布人编号*/
    private int uid;
    /**子孩子*/
    private List<com.sixin.village.domain.SocialcircleComment> child;


    @Override
    public String toString() {
        return "Socialcircle{" +
                "pcid=" + pcid +
                ", pcsennum=" + pcsennum +
                ", aid='" + aid + '\'' +
                ", videourl='" + videourl + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", adddate='" + adddate + '\'' +
                ", pcontent='" + pcontent + '\'' +
                ", uid=" + uid +
                ", child=" + child +
                '}';
    }

    public Socialcircle() {
    }

    public Socialcircle(int pcid, int pcsennum, String aid, String videourl,
                        String imgurl, String adddate, String pcontent,
                        int uid, List<com.sixin.village.domain.SocialcircleComment> child) {
        this.pcid = pcid;
        this.pcsennum = pcsennum;
        this.aid = aid;
        this.videourl = videourl;
        this.imgurl = imgurl;
        this.adddate = adddate;
        this.pcontent = pcontent;
        this.uid = uid;
        this.child = child;
    }

    public List<com.sixin.village.domain.SocialcircleComment> getChild() {
        return child;
    }

    public void setChild(List<com.sixin.village.domain.SocialcircleComment> child) {
        this.child = child;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public int getPcsennum() {
        return pcsennum;
    }

    public void setPcsennum(int pcsennum) {
        this.pcsennum = pcsennum;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }


}
