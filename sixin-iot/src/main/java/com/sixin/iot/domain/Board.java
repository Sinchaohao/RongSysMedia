package com.sixin.iot.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class Board extends BaseEntity
{
    @Excel(name="序号")
    private Long id;
    @Excel(name="测试类型")
    private String ctype;
    @Excel(name="设备IMEI")
    private String cid;
    private String imei;
    @Excel(name="主要功能")
    private String cfun;
    @Excel(name="采集IP")
    private String conip;
    @Excel(name="当前状态")
    private String status;
    @Excel(name="安装时间")
    private Date startdate;
    @Excel(name="更换时间")
    private Date exdate;
    @Excel(name="测试部门号")
    private Long deptid;
    @Excel(name="录入人编号")
    private String uid;
    @Excel(name="备注")
    private String demo;
    @Excel(name="是否有效")
    private String delflag;
    @Excel(name="录入人")
    private String user_name;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("ctype", getCtype())
                .append("imei", getImei())
                .append("cid", getCid())
                .append("cfun", getCfun())
                .append("conip", getConip())
                .append("status", getStatus())
                .append("startdate", getStartdate())
                .append("exdate", getExdate())
                .append("deptid", getDeptid())
                .append("uid", getUid())
                .append("demo", getDemo())
                .append("delflag", getDelflag())
                .append("user_name", getUser_name())
                .toString();
    }

    public Board(){ super();}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCfun() {
        return cfun;
    }

    public void setCfun(String cfun) {
        this.cfun = cfun;
    }

    public String getConip() {
        return conip;
    }

    public void setConip(String conip) {
        this.conip = conip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getExdate() {
        return exdate;
    }

    public void setExdate(Date exdate) {
        this.exdate = exdate;
    }

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}