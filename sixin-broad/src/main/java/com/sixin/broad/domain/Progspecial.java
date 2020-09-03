package com.sixin.broad.domain;

/**
 * @author 张超 teavamc
 * @Description: 特种节目
 * @ClassName Progspecial
 * @date 2019/3/2 12:13
 **/
public class Progspecial {

    private String sid;//特种节目编号
    private String fid;//所属节目编号
    private String sname;//节目名称
    private String address;//节目绝对地址
    private String urls;//节目相对地址
    private String userid;//所属用户
    private String flenth;//节目长度
    private Double fsize;//节目长度
    private String uptime;//上传时间
    private Boolean replacetime;//替换时间
    private String remark;//备注
    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public String getFid() {
        return fid;
    }
    public void setFid(String fid) {
        this.fid = fid;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getUrls() {
        return urls;
    }
    public void setUrls(String urls) {
        this.urls = urls;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getFlenth() {
        return flenth;
    }
    public void setFlenth(String flenth) {
        this.flenth = flenth;
    }
    public String getUptime() {
        return uptime;
    }
    public void setUptime(String uptime) {
        this.uptime = uptime;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Boolean getReplacetime() {
        return replacetime;
    }
    public void setReplacetime(Boolean replacetime) {
        this.replacetime = replacetime;
    }
    public Double getFsize() {
        return fsize;
    }
    public void setFsize(Double fsize) {
        this.fsize = fsize;
    }

}
