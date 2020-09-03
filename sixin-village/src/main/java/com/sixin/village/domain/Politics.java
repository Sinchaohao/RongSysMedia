package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;
/**
 * @Description: 政策表$
 * @Param: dyq_RongSys$
 * @return: $
 * @Author: Mr.DYQ
 * @Date: $
 */
public class Politics extends BaseEntity{
    private static final long serialVersionUID = 1L;
    /**政策发布编号*/
    private String shiid;
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
    /**图片*/
    private String pic;
    /**文章浏览数目*/
    private  int courread;
    /**添加人编号*/
    private int uid;
    /**添加人姓名*/
    private String uname;
    /**是否有效*/
    private String remark;
    /**用户所属地区村社区 aid*/
    private String aid;
    /**是否有效*/
    private String video;

    @Override
    public String toString() {
        return "Politics{" +
                "shiid=" + shiid +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", adddate='" + adddate + '\'' +
                ", reldate='" + reldate + '\'' +
                ", content='" + content + '\'' +
                ", pic='" + pic + '\'' +
                ", courread=" + courread +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", remark='" + remark + '\'' +
                ", aid='" + aid + '\'' +
                ", video='" + video + '\'' +
                '}';
    }

    public Politics(String shiid, String title, String type,
                    String adddate, String reldate,
                    String content, String pic, int courread,
                    int uid, String uname, String remark, String aid, String vedio) {
        this.shiid = shiid;
        this.title = title;
        this.type = type;
        this.adddate = adddate;
        this.reldate = reldate;
        this.content = content;
        this.pic = pic;
        this.courread = courread;
        this.uid = uid;
        this.uname = uname;
        this.remark = remark;
        this.aid = aid;
        this.video = video;
    }

    public Politics() {
        super();
    }

    public String getShiid() {
        return shiid;
    }

    public void setShiid(String shiid) {
        this.shiid = shiid;
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

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
