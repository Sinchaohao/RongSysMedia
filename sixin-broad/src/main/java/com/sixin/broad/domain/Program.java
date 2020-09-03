package com.sixin.broad.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;

/**
 * @program: RongSys-lyb
 * @description: 节目上传
 * @author: Mr.Liu
 * @create: 2019-02-26 19:30
 **/
public class Program extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Excel(name = "节目申请-节目ID")
    private String fid;//节目申请-节目ID
    @Excel(name = "节目名称")
    private String fname;//节目名称
    @Excel(name = "用户ID")
    private String userid;//用户ID
    @Excel(name = "备注")
    private String remark;//备注
    @Excel(name = "是否公共")
    private Boolean ispublic;//是否公共
    @Excel(name = "保存名称")
    private String filename;//要求文稿名
    private String address;//要求文稿路径
    @Excel(name = "上传路径")
    private String urls;//要求文稿路径
    @Excel(name = "创建时间")
    private String createdtime;//申请提交时间
    @Excel(name = "节目时长")
    private String flenth;//节目时长
    @Excel(name = "文件大小")
    private Double fsize;//文件大小
    private Boolean islisten;//是否审听
    private Boolean ptype;//节目类型，是否是录制节目
    @Excel(name = "上传用户名")
    private String uname;//上传用户名
    private String downtime;
    private String endtime;

    public String getDowntime() {
        return downtime;
    }

    public void setDowntime(String downtime) {
        this.downtime = downtime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getIspublic() {
        return ispublic;
    }

    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getFlenth() {
        return flenth;
    }

    public void setFlenth(String flenth) {
        this.flenth = flenth;
    }

    public Double getFsize() {
        return fsize;
    }

    public void setFsize(Double fsize) {
        this.fsize = fsize;
    }

    public Boolean getIslisten() {
        return islisten;
    }

    public void setIslisten(Boolean islisten) {
        this.islisten = islisten;
    }

    public Boolean getPtype() {
        return ptype;
    }

    public void setPtype(Boolean ptype) {
        this.ptype = ptype;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "Program{" +
                "fid='" + fid + '\'' +
                ", fname='" + fname + '\'' +
                ", userid='" + userid + '\'' +
                ", remark='" + remark + '\'' +
                ", ispublic=" + ispublic +
                ", filename='" + filename + '\'' +
                ", address='" + address + '\'' +
                ", urls='" + urls + '\'' +
                ", createdtime='" + createdtime + '\'' +
                ", flenth='" + flenth + '\'' +
                ", fsize=" + fsize +
                ", islisten=" + islisten +
                ", ptype=" + ptype +
                ", uname='" + uname + '\'' +
                '}';
    }
}
