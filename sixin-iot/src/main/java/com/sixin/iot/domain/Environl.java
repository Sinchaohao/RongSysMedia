package com.sixin.iot.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.system.domain.SysDept;

public class Environl {
    @Excel(name = "编号")
    private Long id;
    @Excel(name = "终端编号")
    private String imei;
    @Excel(name = "环境温度")
    private Float ambient_temp;
    @Excel(name = "环境湿度")
    private Float ambient_hum;
    @Excel(name = "环境照度")
    private Float ambient_light;
    @Excel(name = "土壤温度")
    private Float soil_temp;
    @Excel(name = "土壤湿度")
    private Float soil_hum;
    @Excel(name = "大气压力")
    private Float atmo_pressure;
    @Excel(name = "风速")
    private Float wind_speed;
    @Excel(name = "风向")
    private String wind_direction;
    @Excel(name = "雨雪")
    private String snow_rain;
    @Excel(name = "PM2.5")
    private Float pm25;
    @Excel(name = "二氧化碳浓度")
    private Float co2;
    @Excel(name = "一氧化碳浓度")
    private Float co;
    @Excel(name = "二氧化硫浓度")
    private Float so2;
    @Excel(name = "采集时间")
    private String colltime;
    private String endtime;
    @Excel(name = "设备状态")
    private String status;
    @Excel(name = "录入人编号")
    private String uid;
    @Excel(name = "录入人姓名")
    private String user_name;
    @Excel(name = "地域编号")
    private String deptid;
    private SysDept dept;

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "Environl{" +
                "id='" + id + '\'' +
                ", imei='" + imei + '\'' +
                ", ambient_temp='" + ambient_temp + '\'' +
                ", ambient_hum='" + ambient_hum + '\'' +
                ", ambient_light='" + ambient_light + '\'' +
                ", soil_hum='" + soil_hum + '\'' +
                ", atmo_pressure='" + atmo_pressure + '\'' +
                ", wind_speed='" + wind_speed + '\'' +
                ", wind_direction='" + wind_direction + '\'' +
                ", soil_temp='" + soil_temp + '\'' +
                ", snow_rain='" + snow_rain + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", co2='" + co2 + '\'' +
                ", co='" + co + '\'' +
                ", so2='" + so2 + '\'' +
                ", colltime='" + colltime + '\'' +
                ", status='" + status + '\'' +
                ", username='" + user_name + '\'' +
                ", deptid='" + deptid + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }


    public Environl() {
        super();
    }

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

    public Float getAmbient_temp() {
        return ambient_temp;
    }

    public void setAmbient_temp(Float ambient_temp) {
        this.ambient_temp = ambient_temp;
    }

    public Float getAmbient_hum() {
        return ambient_hum;
    }

    public void setAmbient_hum(Float ambient_hum) {
        this.ambient_hum = ambient_hum;
    }

    public Float getAmbient_light() {
        return ambient_light;
    }

    public void setAmbient_light(Float ambient_light) {
        this.ambient_light = ambient_light;
    }

    public Float getSoil_temp() {
        return soil_temp;
    }

    public void setSoil_temp(Float soil_temp) {
        this.soil_temp = soil_temp;
    }

    public Float getSoil_hum() {
        return soil_hum;
    }

    public void setSoil_hum(Float soil_hum) {
        this.soil_hum = soil_hum;
    }

    public Float getAtmo_pressure() {
        return atmo_pressure;
    }

    public void setAtmo_pressure(Float atmo_pressure) {
        this.atmo_pressure = atmo_pressure;
    }

    public Float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(Float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getSnow_rain() {
        return snow_rain;
    }

    public void setSnow_rain(String snow_rain) {
        this.snow_rain = snow_rain;
    }

    public Float getPm25() {
        return pm25;
    }

    public void setPm25(Float pm25) {
        this.pm25 = pm25;
    }

    public Float getCo2() {
        return co2;
    }

    public void setCo2(Float co2) {
        this.co2 = co2;
    }

    public Float getCo() {
        return co;
    }

    public void setCo(Float co) {
        this.co = co;
    }

    public Float getSo2() {
        return so2;
    }

    public void setSo2(Float so2) {
        this.so2 = so2;
    }

    public String getColltime() {
        return colltime;
    }

    public void setColltime(String colltime) {
        this.colltime = colltime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return user_name;
    }

    public void setUsername(String username) {
        this.user_name = username;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }
}