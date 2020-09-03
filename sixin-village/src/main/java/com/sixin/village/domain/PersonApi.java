package com.sixin.village.domain;

import java.util.List;

public class PersonApi {
    private Integer userid; //用户id
    private String fname; //节目名称
    private String cname; //电台名称
    private String aid; //地区编号
    private String uname; //姓名
    private String sex; //姓名
    private String politics;//政治面貌
    private Integer pageSize;
    private Integer pageIndex;
    private List<String> listaid;
    private String state;//
    private Integer minage;
    private Integer maxage;
    private String tid; //终端号
    private List<String> tids;  //终端号集合

    public List<String> getTids() {
        return tids;
    }

    public void setTids(List<String> tids) {
        this.tids = tids;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public List<String> getListaid() {
        return listaid;
    }

    public void setListaid(List<String> listaid) {
        this.listaid = listaid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getMinage() {
        return minage;
    }

    public void setMinage(Integer minage) {
        this.minage = minage;
    }

    public Integer getMaxage() {
        return maxage;
    }

    public void setMaxage(Integer maxage) {
        this.maxage = maxage;
    }
}
