package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: lyb_RongSys
 * @description: 小村家事
 * @author: Mr.Liu
 * @create: 2019-01-15 14:52
 **/
public class Villagefamily extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**小村家事数据编号*/
    private int jsid;
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
    /**添加人编号*/
    private int adduid;
    /**添加人姓名*/
    private String adduname;
    /**发送人编号*/
    private int uid;
    /**发送人姓名*/
    private String uname;
    /**用户所属地区村社区 aid*/
    private String aid;
    /**是否有效*/
    private String remark;
    /**图片路径*/
    private String pic;
    /**阅读量*/
    private int readtotal;

    public Villagefamily() {
    }

    @Override
    public String toString() {
        return "Villagefamily{" +
                "jsid=" + jsid +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", adddate='" + adddate + '\'' +
                ", reldate='" + reldate + '\'' +
                ", content='" + content + '\'' +
                ", adduid=" + adduid +
                ", adduname='" + adduname + '\'' +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", aid='" + aid + '\'' +
                ", remark=" + remark +
                ", pic=" + pic +
                ", readtotal=" + readtotal +
                '}';
    }

    public Villagefamily(int jsid, String title, String type,
                         String adddate, String reldate,
                         String content, int adduid,
                         String adduname, int uid, String uname,
                         String aid, String remark, String pic, int readtotal) {
        this.jsid = jsid;
        this.title = title;
        this.type = type;
        this.adddate = adddate;
        this.reldate = reldate;
        this.content = content;
        this.adduid = adduid;
        this.adduname = adduname;
        this.uid = uid;
        this.uname = uname;
        this.aid = aid;
        this.remark = remark;
        this.pic = pic;
        this.readtotal = readtotal;
    }

    public int getJsid() {
        return jsid;
    }

    public void setJsid(int jsid) {
        this.jsid = jsid;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getReadtotal() {
        return readtotal;
    }

    public void setReadtotal(int readtotal) {
        this.readtotal = readtotal;
    }


}

