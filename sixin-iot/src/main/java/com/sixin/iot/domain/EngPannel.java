package com.sixin.iot.domain;

//import javax.swing.text.html.parser.Entity;
import com.sixin.common.base.BaseEntity;
import com.sixin.system.domain.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Date;

public class EngPannel extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String imei;
    private String addrid;
    private float pow1;
    private float grouppow;
    private float vol18v1;
    private float vol18v2;
    private float vol24;
    private float vol28;
    private float extendpow;
    private float solarpow;
    private float operating_temp;
    private float charge;
    private int work;
    private Date colltime;
    private SysDept dept;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getAddrid() {
        return addrid;
    }

    public void setAddrid(String addrid) {
        this.addrid = addrid;
    }

    public float getPow1() {
        return pow1;
    }

    public void setPow1(float pow1) {
        this.pow1 = pow1;
    }

    public float getGrouppow() {
        return grouppow;
    }

    public void setGrouppow(float grouppow) {
        this.grouppow = grouppow;
    }

    public float getVol18v1() {
        return vol18v1;
    }

    public void setVol18v1(float vol18v1) {
        this.vol18v1 = vol18v1;
    }

    public float getVol18v2() {
        return vol18v2;
    }

    public void setVol18v2(float vol18v2) {
        this.vol18v2 = vol18v2;
    }

    public float getVol24() {
        return vol24;
    }

    public void setVol24(float vol24) {
        this.vol24 = vol24;
    }

    public float getVol28() {
        return vol28;
    }

    public void setVol28(float vol28) {
        this.vol28 = vol28;
    }

    public float getExtendpow() {
        return extendpow;
    }

    public void setExtendpow(float extendpow) {
        this.extendpow = extendpow;
    }

    public float getSolarpow() {
        return solarpow;
    }

    public void setSolarpow(float solarpow) {
        this.solarpow = solarpow;
    }

    public float getOperating_temp() {
        return operating_temp;
    }

    public void setOperating_temp(float operating_temp) {
        this.operating_temp = operating_temp;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public int getWork() {
        return work;
    }

    public void setWork(int work) {
        this.work = work;
    }

    public Date getColltime() {
        return colltime;
    }

    public void setColltime(Date colltime) {
        this.colltime = colltime;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("imei", getImei())
                .append("addrid",getAddrid())
                .append("pow1", getPow1())
                .append("grouppow",getGrouppow())
                .append("vol18v1",getVol18v1())
                .append("vol18v2",getVol18v2())
                .append("vol24",getVol24())
                .append("vol28",getVol28())
                .append("extendpow",getExtendpow())
                .append("solarpow",getSolarpow())
                .append("operating_temp",getOperating_temp())
                .append("charge", getCharge())
                .append("work", getWork())
                .append("colltime", getColltime())
                .append("dept",getDept())
                .toString();
    }
}
