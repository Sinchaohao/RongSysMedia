package com.sixin.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Date;

public class SysPerson {
    private Long id;
    private String pid;
    private String deptid;
    private String pname;
    private String phone;
    private String ptype;
    private String stationcode;
    private int redius;
    private String address;
    private Date startdate;
    private Date enddate;
    private int remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getStationcode() {
        return stationcode;
    }

    public void setStationcode(String stationcode) {
        this.stationcode = stationcode;
    }

    public int getRedius() {
        return redius;
    }

    public void setRedius(int redius) {
        this.redius = redius;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("pid",getPid())
                .append("deptid",getDeptid())
                .append("pname",getPname())
                .append("phone",getPhone())
                .append("ptype",getPtype())
                .append("stationcode",getStationcode())
                .append("redius",getRedius())
                .append("address",getAddress())
                .append("startdate",getStartdate())
                .append("enddate",getEnddate())
                .append("remark",getRemark())
                .toString();
    }
}
