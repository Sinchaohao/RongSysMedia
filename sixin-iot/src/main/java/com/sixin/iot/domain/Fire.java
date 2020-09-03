package com.sixin.iot.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Fire extends BaseEntity {
    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "类型")
    private String ctype;
    @Excel(name = "存放编号")
    private String cid;
    @Excel(name = "数据值")
    private String vdata;
    @Excel(name = "数据单位")
    private String vunit;
    @Excel(name = "数据状态",readConverterExp = "0=停用,1=正常")
    private String status;
    @Excel(name = "采集时间")
    private String colldate;
    @Excel(name = "采集IP")
    private String collip;
    @Excel(name = "安装时间")
    private String startdate;
    @Excel(name = "更换时间")
    private String exdate;
    @Excel(name = "存放位置编号")
    private String deptid;
    @Excel(name = "录入人姓名")
    private String user_name;
    @Excel(name = "录入人编号")
    private String uid;
    @Excel(name = "备注")
    private String demo;
    @Excel(name = "是否有效")
    private String delflag;


    public Fire()
    {

    }
    public Fire(Integer id)
    {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getVdata() {
        return vdata;
    }

    public void setVdata(String vdata) {
        this.vdata = vdata;
    }

    public String getVunit() {
        return vunit;
    }

    public void setVunit(String vunit) {
        this.vunit = vunit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColldate() {
        return colldate;
    }

    public void setColldate(String colldate) {
        this.colldate = colldate;
    }

    public String getCollip() {
        return collip;
    }

    public void setCollip(String collip) {
        this.collip = collip;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getExdate() {
        return exdate;
    }

    public void setExdate(String exdate) {
        this.exdate = exdate;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("ctype", getCtype())
                .append("cid", getCid())
                .append("vdata", getVdata())
                .append("vunit", getVunit())
                .append("status", getStatus())
                .append("colldate", getColldate())
                .append("collip", getCollip())
                .append("startdate", getStartdate())
                .append("exdate", getExdate())
                .append("deptid", getDeptid())
                .append("user_name", getUser_name())
                .append("demo", getDemo())
                .append("delflag", getDelflag())
                .toString();
    }
}