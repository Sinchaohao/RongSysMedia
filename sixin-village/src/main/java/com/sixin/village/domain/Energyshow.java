package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;
import java.util.Date;

/**
 * 能源管理终端采集信息表energy
 *
 * @author 薛莎莎
 * @date 2019-11-23
 */

public class Energyshow extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**终端设备IMEI*/
    private String tid;
    /**采集地址编号*/
    private Integer addrid;
    /**采集时间*/
    private Date colltime;
    /**电池1电压*/
    private String fvoltage;
    /**电池组电压*/
    private String gvoltage;
    /**充电电流*/
    private String rechcurrent;
    /**负载电流*/
    private String loadcurrent;
    /**输出电压*/
    private String outvoltage;
    /**外部电源电压*/
    private String extvoltage;
    /**太阳能电压*/
    private String sunvoltage;
    /**工作温度*/
    private String worktemp;
    /**充电状态*/
    private Integer rechstatus;
    /**工作状态*/
    private Integer workstatus;
    /**信息状态*/
    private String remark;

    /**用户id*/
    private String userid;
    /**安装位置*/
    private String address;
    /**终端号码*/
    private String phone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Integer getAddrid() {
        return addrid;
    }

    public void setAddrid(Integer addrid) {
        this.addrid = addrid;
    }

    public Date getColltime() {
        return colltime;
    }

    public void setColltime(Date colltime) {
        this.colltime = colltime;
    }

    public String getFvoltage() {
        return fvoltage;
    }

    public void setFvoltage(String fvoltage) {
        this.fvoltage = fvoltage;
    }

    public String getGvoltage() {
        return gvoltage;
    }

    public void setGvoltage(String gvoltage) {
        this.gvoltage = gvoltage;
    }

    public String getRechcurrent() {
        return rechcurrent;
    }

    public void setRechcurrent(String rechcurrent) {
        this.rechcurrent = rechcurrent;
    }

    public String getLoadcurrent() {
        return loadcurrent;
    }

    public void setLoadcurrent(String loadcurrent) {
        this.loadcurrent = loadcurrent;
    }

    public String getOutvoltage() {
        return outvoltage;
    }

    public void setOutvoltage(String outvoltage) {
        this.outvoltage = outvoltage;
    }

    public String getExtvoltage() {
        return extvoltage;
    }

    public void setExtvoltage(String extvoltage) {
        this.extvoltage = extvoltage;
    }

    public String getSunvoltage() {
        return sunvoltage;
    }

    public void setSunvoltage(String sunvoltage) {
        this.sunvoltage = sunvoltage;
    }

    public String getWorktemp() {
        return worktemp;
    }

    public void setWorktemp(String worktemp) {
        this.worktemp = worktemp;
    }

    public Integer getRechstatus() {
        return rechstatus;
    }

    public void setRechstatus(Integer rechstatus) {
        this.rechstatus = rechstatus;
    }

    public Integer getWorkstatus() {
        return workstatus;
    }

    public void setWorkstatus(Integer workstatus) {
        this.workstatus = workstatus;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Energyshow{" +
                "id=" + id +
                ", tid=" + tid +
                ", addrid=" + addrid +
                ", colltime=" + colltime +
                ", fvoltage='" + fvoltage + '\'' +
                ", gvoltage='" + gvoltage + '\'' +
                ", rechcurrent='" + rechcurrent + '\'' +
                ", loadcurrent='" + loadcurrent + '\'' +
                ", outvoltage='" + outvoltage + '\'' +
                ", extvoltage='" + extvoltage + '\'' +
                ", sunvoltage='" + sunvoltage + '\'' +
                ", worktemp='" + worktemp + '\'' +
                ", rechstatus=" + rechstatus +
                ", workstatus=" + workstatus +
                ", remark='" + remark + '\'' +
                '}';
    }
}
