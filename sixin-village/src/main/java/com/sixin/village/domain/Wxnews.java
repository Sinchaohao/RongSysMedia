package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;
/**
 * 微信公众号新闻列表 tb_wxnews
 *
 * @author hfz
 * @date 2020-1-8
 */
public class Wxnews extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**编号*/
    private Integer id;
    /**标题*/
    private String title;
    /**内容*/
    private String content;
    /**图片路径*/
    private String pic;
    /**文件名*/
    private String filename;
    /**文件长度*/
    private String flength;
    /**新闻类型*/
    private String ftype;
    /**添加时间*/
    private String addate;
    /**修改时间*/
    private String modydate;
    /**阅读数*/
    private Integer readers;
    /**发布者编号*/
    private String userid;
    /**阅读者地域编号*/
    private String aid;
    /**是否公共*/
    private Integer ispub;
    /**是否有效*/
    private String remark;

    private Integer pageSize;
    private Integer pageIndex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFlength() {
        return flength;
    }

    public void setFlength(String flength) {
        this.flength = flength;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getAddate() {
        return addate;
    }

    public void setAddate(String addate) {
        this.addate = addate;
    }

    public String getModydate() {
        return modydate;
    }

    public void setModydate(String modydate) {
        this.modydate = modydate;
    }

    public Integer getReaders() {
        return readers;
    }

    public void setReaders(Integer readers) {
        this.readers = readers;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public Integer getIspub() {
        return ispub;
    }

    public void setIspub(Integer ispub) {
        this.ispub = ispub;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        return "Wxnews{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", pic='" + pic + '\'' +
                ", filename='" + filename + '\'' +
                ", flength='" + flength + '\'' +
                ", ftype='" + ftype + '\'' +
                ", addate=" + addate +
                ", modydate=" + modydate +
                ", readers=" + readers +
                ", uid='" + userid + '\'' +
                ", aid='" + aid + '\'' +
                ", ispub=" + ispub +
                ", remark='" + remark + '\'' +
                '}';
    }
}
