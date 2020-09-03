package com.sixin.iot.domain;
import com.sixin.common.annotation.Excel;

import java.util.Date;

public class Energy {
    private static final long serialVersioncharge = 1L;
    @Excel(name="编号")
    private Integer  id;
    @Excel(name="终端编号")
    private String imei;
    @Excel(name="电池1电压")
    private String pow1;
    @Excel(name="电池组电压")
    private String grouppow;
    @Excel(name="18V1输出电压")
    private String vol18v1;
    @Excel(name="18V2输出电压")
    private String vol18v2;
    @Excel(name="24V输出电压")
    private String vol24;
    @Excel(name="28V输出电压")
    private String vol28;
    @Excel(name="外部电源电压")
    private String extendpow;
    @Excel(name="太阳能电压")
    private String solarpow;
    @Excel(name="工作温度")
    private String operating_temp;
    @Excel(name="充电状况")
    private String charge;
    @Excel(name="工作状况")
    private Integer work;
    @Excel(name="采集时间")
    private Date colltime;
    private String endtime;
    private String deptid;
    private String status;

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public static long getSerialVersioncharge() {
        return serialVersioncharge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPow1() {
        return pow1;
    }

    public void setPow1(String pow1) {
        this.pow1 = pow1;
    }

    public String getGrouppow() {
        return grouppow;
    }

    public void setGrouppow(String grouppow) {
        this.grouppow = grouppow;
    }

    public String getVol18v1() {
        return vol18v1;
    }

    public void setVol18v1(String vol18v1) {
        this.vol18v1 = vol18v1;
    }

    public String getVol18v2() {
        return vol18v2;
    }

    public void setVol18v2(String vol18v2) {
        this.vol18v2 = vol18v2;
    }

    public String getVol24() {
        return vol24;
    }

    public void setVol24(String vol24) {
        this.vol24 = vol24;
    }

    public String getVol28() {
        return vol28;
    }

    public void setVol28(String vol28) {
        this.vol28 = vol28;
    }

    public String getExtendpow() {
        return extendpow;
    }

    public void setExtendpow(String extendpow) {
        this.extendpow = extendpow;
    }

    public String getSolarpow() {
        return solarpow;
    }

    public void setSolarpow(String solarpow) {
        this.solarpow = solarpow;
    }

    public String getOperating_temp() {
        return operating_temp;
    }

    public void setOperating_temp(String operating_temp) {
        this.operating_temp = operating_temp;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    public Date getColltime() {
        return colltime;
    }

    public void setColltime(Date colltime) {
        this.colltime = colltime;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Energy{" +
                "id=" + id +
                ", imei='" + imei + '\'' +
                ", pow1='" + pow1 + '\'' +
                ", grouppow='" + grouppow + '\'' +
                ", vol18v1='" + vol18v1 + '\'' +
                ", vol18v2='" + vol18v2 + '\'' +
                ", vol24='" + vol24 + '\'' +
                ", vol28='" + vol28 + '\'' +
                ", extendpow='" + extendpow + '\'' +
                ", solarpow='" + solarpow + '\'' +
                ", operating_temp='" + operating_temp + '\'' +
                ", charge='" + charge + '\'' +
                ", work=" + work +
                ", colltime='" + colltime + '\'' +
                ", deptid='" + deptid + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}