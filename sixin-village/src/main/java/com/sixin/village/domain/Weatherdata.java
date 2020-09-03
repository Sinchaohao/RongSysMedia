package com.sixin.village.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sixin.common.base.BaseEntity;

import java.util.Date;

/**
 * 采集天气信息表 tb_weatherdata
 *
 * @author hfz
 * @date 2019-10-16
 */
public class Weatherdata extends BaseEntity{
    private static final long serialVersionUID = 1L;
    /**用户id*/
    private String userid;
    /**编号*/
    private Integer id;
    /**终端设备IMEI*/
    private String tid;
    /**采集地址编号*/
    private String addrid;
    /**采集地区区域名*/
    private String aname;
    /**采集时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date colltime;
    /**环境温度*/
    private float surrtemp;
    /**环境湿度*/
    private float surrhumi;
    /**环境照度*/
    private float surrlight;
    /**土壤温度*/
    private float soiltemp;
    /**土壤湿度*/
    private float soilhumi;
    /**大气压力*/
    private float atmopress;
    /**风速*/
    private float windspeed;
    /**风向*/
    private String windire;
    /**天气类型*/
    private String weathertype;
    /**PM2.5浓度*/
    private float pm25;
    /**二氧化碳浓度*/
    private float co2;
    /**一氧化碳浓度*/
    private float co;
    /**二氧化硫浓度*/
    private float so2;
    /**氨气浓度*/
    private float ch4;
    /**下雨量*/
    private float rainfall;
    /**雨水PH值*/
    private float ph;
    /**信息状态 */
    private String remark;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

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

    public String getAddrid() {
        return addrid;
    }

    public void setAddrid(String addrid) {
        this.addrid = addrid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Date getColltime() {
        return colltime;
    }

    public void setColltime(Date colltime) {
        this.colltime = colltime;
    }

    public float getSurrtemp() {
        return surrtemp;
    }

    public void setSurrtemp(float surrtemp) {
        this.surrtemp = surrtemp;
    }

    public float getSurrhumi() {
        return surrhumi;
    }

    public void setSurrhumi(float surrhumi) {
        this.surrhumi = surrhumi;
    }

    public float getSurrlight() {
        return surrlight;
    }

    public void setSurrlight(float surrlight) {
        this.surrlight = surrlight;
    }

    public float getSoiltemp() {
        return soiltemp;
    }

    public void setSoiltemp(float soiltemp) {
        this.soiltemp = soiltemp;
    }

    public float getSoilhumi() {
        return soilhumi;
    }

    public void setSoilhumi(float soilhumi) {
        this.soilhumi = soilhumi;
    }

    public float getAtmopress() {
        return atmopress;
    }

    public void setAtmopress(float atmopress) {
        this.atmopress = atmopress;
    }

    public float getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(float windspeed) {
        this.windspeed = windspeed;
    }

    public String getWindire() {
        return windire;
    }

    public void setWindire(String windire) {
        this.windire = windire;
    }

    public String getWeathertype() {
        return weathertype;
    }

    public void setWeathertype(String weathertype) {
        this.weathertype = weathertype;
    }

    public float getPm25() {
        return pm25;
    }

    public void setPm25(float pm25) {
        this.pm25 = pm25;
    }

    public float getCo2() {
        return co2;
    }

    public void setCo2(float co2) {
        this.co2 = co2;
    }

    public float getCo() {
        return co;
    }

    public void setCo(float co) {
        this.co = co;
    }

    public float getSo2() {
        return so2;
    }

    public void setSo2(float so2) {
        this.so2 = so2;
    }

    public float getCh4() {
        return ch4;
    }

    public void setCh4(float ch4) {
        this.ch4 = ch4;
    }

    public float getRainfall() {
        return rainfall;
    }

    public void setRainfall(float rainfall) {
        this.rainfall = rainfall;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Weatherdata{" +
                "id=" + id +
                ", tid='" + tid + '\'' +
                ", addrid='" + addrid + '\'' +
                ", aname='" + aname + '\'' +
                ", colltime=" + colltime +
                ", surrtemp=" + surrtemp +
                ", surrhumi=" + surrhumi +
                ", surrlight=" + surrlight +
                ", soiltemp=" + soiltemp +
                ", soilhumi=" + soilhumi +
                ", atmopress=" + atmopress +
                ", windspeed=" + windspeed +
                ", windire='" + windire + '\'' +
                ", weathertype='" + weathertype + '\'' +
                ", pm25=" + pm25 +
                ", co2=" + co2 +
                ", co=" + co +
                ", so2=" + so2 +
                ", ch4=" + ch4 +
                ", rainfall=" + rainfall +
                ", ph=" + ph +
                ", remark='" + remark + '\'' +
                '}';
    }
}
