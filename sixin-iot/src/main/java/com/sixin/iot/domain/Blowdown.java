package com.sixin.iot.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
public class Blowdown extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Excel(name="序号")
    private Long id;
    @Excel(name="测试类型")
    private String ctype;
    @Excel(name="存放编号")
    private String cid;
    @Excel(name="测试数据")
    private String vdata;
    @Excel(name="测试单位")
    private String vunit;
    @Excel(name="采集状态")
    private String status;
    @Excel(name="采集时间")
    private Date colldate;
    @Excel(name="采集IP")
    private String collip;
    @Excel(name="安装时间")
    private Date startdate;
    @Excel(name="更换时间")
    private Date exdate;
    @Excel(name="测试部门号")
    private Long deptid;
    @Excel(name="录入人编号")
    private Long uid;
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
                .append("cid", getCid())
                .append("vdata", getVdata())
                .append("vunit", getVunit())
                .append("status", getStatus())
                .append("colldate", getColldate())
                .append("collip", getCollip())
                .append("startdate", getStartdate())
                .append("exdate", getExdate())
                .append("deptid", getDeptid())
                .append("uid", getUid())
                .append("demo", getDemo())
                .append("delflag", getDelflag())
                .append("user_name", getUser_name())
                .toString();
    }
    public Blowdown() {
        super();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public boolean isAdmin()
    {
        return isAdmin(this.uid);
    }
    public static boolean isAdmin(Long uid)
    {
        return uid != null && 1L == uid;
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

    public Date getColldate() {
        return colldate;
    }

    public void setColldate(Date colldate) {
        this.colldate = colldate;
    }

    public String getCollip() {
        return collip;
    }

    public void setCollip(String collip) {
        this.collip = collip;
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
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
