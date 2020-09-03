package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;
/**
 * @Description: 公告信息表$
 * @Param: $
 * @return: $
 * @Author: Mr.DYQ
 * @Date: $
 */
public class Policyinfo extends BaseEntity{
    private static final long serialVersionUID = 1L;
    /**公告发布编号*/
    private String poinid;
    /**文章标题*/
    private String title;
    /**文章类型*/
    private String type;
    /**添加日期*/
    private String adddate;
    /**修改日期*/
    private String reldate;
    /**文章内容*/
    private String content;
    /***添加人编号*/
    private int adduid;
    /**添加人用户名*/
    private String adduname;
    /**图片*/
    private String pic;
    /**文章浏览数目*/
    private  int courread;
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
        return "Policyinfo{" +
                "poinid='" + poinid + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", adddate='" + adddate + '\'' +
                ", reldate='" + reldate + '\'' +
                ", content='" + content + '\'' +
                ", adduid=" + adduid +
                ", adduname=" + adduname +
                ", pic='" + pic + '\'' +
                ", courread=" + courread +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", aid='" + aid + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Policyinfo(String poinid, String title, String type,
                      String adddate, String reldate, String content,
                      int adduid, String adduname, String pic, int courread,
                      int uid, String uname, String aid, String remark) {
        this.poinid = poinid;
        this.title = title;
        this.type = type;
        this.adddate = adddate;
        this.reldate = reldate;
        this.content = content;
        this.adduid = adduid;
        this.adduname = adduname;
        this.pic = pic;
        this.courread = courread;
        this.uid = uid;
        this.uname = uname;
        this.aid = aid;
        this.remark = remark;
    }
    public Policyinfo() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPoinid() {
        return poinid;
    }

    public void setPoinid(String poinid) {
        this.poinid = poinid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getReldate() {
        return reldate;
    }

    public void setReldate(String reldate) {
        this.reldate = reldate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAdduid() {
        return adduid;
    }

    public void setAdduid(int adduid) {
        this.adduid = adduid;
    }

    public String getAdduname() {
        return adduname;
    }

    public void setAdduname(String adduname) {
        this.adduname = adduname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getCourread() {
        return courread;
    }

    public void setCourread(int courread) {
        this.courread = courread;
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
