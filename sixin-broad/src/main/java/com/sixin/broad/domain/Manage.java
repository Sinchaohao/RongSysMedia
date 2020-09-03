package com.sixin.broad.domain;

import com.sixin.common.base.BaseEntity;

/**
 * @author cx
 * @Time 2019/8/20 15:15
 * @Description 管理参数列表
 */
public class Manage extends BaseEntity{

    /*IEME码*/
    private String tid;

    /*终端名称*/
    private String tname;

    private String userid;

    private String aid;

    private String aname;

    private String createdtime;

    /*安装点经度*/
    private String Longitude;

    /*安装点纬度*/
    private String Latitude;

    /*覆盖用户数*/
    private String Families;

    /*覆盖人口数*/
    private String Population;

    /*二次覆盖模式*/
    private String TwoOverlayMode;

    private String TwoTransmitFrequency;

    /*二次覆盖终端数*/
    private int TwoOverlayNumber;

    /*二次覆盖用户数*/
    private int TwoOverlayFamiles;

    /*二次覆盖人口数*/
    private int TwoOverlayPopulations;

    /*耗电指标*/
    private double PowerConsumption;

    /*其他应急系统技术模式*/
    private String OtherTechnology;

    /*应急演练期限*/
    private String DrillingPeriod;

    /*备用电频配置*/
    private String StandbyBattery;

    /*回传数据技术模式*/
    private String PlaybacksMode;

    /*维护经费*/
    private String MaintenanceFunds;

    /*备注*/
    private String Remark;

    private String Supervisionunit;

    private String Accountabilityunit;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getFamilies() {
        return Families;
    }

    public void setFamilies(String families) {
        Families = families;
    }

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public String getTwoOverlayMode() {
        return TwoOverlayMode;
    }

    public void setTwoOverlayMode(String twoOverlayMode) {
        TwoOverlayMode = twoOverlayMode;
    }

    public String getTwoTransmitFrequency() {
        return TwoTransmitFrequency;
    }

    public void setTwoTransmitFrequency(String twoTransmitFrequency) {
        TwoTransmitFrequency = twoTransmitFrequency;
    }

    public int getTwoOverlayNumber() {
        return TwoOverlayNumber;
    }

    public void setTwoOverlayNumber(int twoOverlayNumber) {
        TwoOverlayNumber = twoOverlayNumber;
    }

    public int getTwoOverlayFamiles() {
        return TwoOverlayFamiles;
    }

    public void setTwoOverlayFamiles(int twoOverlayFamiles) {
        TwoOverlayFamiles = twoOverlayFamiles;
    }

    public int getTwoOverlayPopulations() {
        return TwoOverlayPopulations;
    }

    public void setTwoOverlayPopulations(int twoOverlayPopulations) {
        TwoOverlayPopulations = twoOverlayPopulations;
    }

    public double getPowerConsumption() {
        return PowerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        PowerConsumption = powerConsumption;
    }

    public String getOtherTechnology() {
        return OtherTechnology;
    }

    public void setOtherTechnology(String otherTechnology) {
        OtherTechnology = otherTechnology;
    }

    public String getDrillingPeriod() {
        return DrillingPeriod;
    }

    public void setDrillingPeriod(String drillingPeriod) {
        DrillingPeriod = drillingPeriod;
    }

    public String getStandbyBattery() {
        return StandbyBattery;
    }

    public void setStandbyBattery(String standbyBattery) {
        StandbyBattery = standbyBattery;
    }

    public String getPlaybacksMode() {
        return PlaybacksMode;
    }

    public void setPlaybacksMode(String playbacksMode) {
        PlaybacksMode = playbacksMode;
    }

    public String getMaintenanceFunds() {
        return MaintenanceFunds;
    }

    public void setMaintenanceFunds(String maintenanceFunds) {
        MaintenanceFunds = maintenanceFunds;
    }

    @Override
    public String getRemark() {
        return Remark;
    }

    @Override
    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getSupervisionunit() {
        return Supervisionunit;
    }

    public void setSupervisionunit(String supervisionunit) {
        Supervisionunit = supervisionunit;
    }

    public String getAccountabilityunit() {
        return Accountabilityunit;
    }

    public void setAccountabilityunit(String accountabilityunit) {
        Accountabilityunit = accountabilityunit;
    }

    @Override
    public String toString() {
        return "Manage{" +
                "tid='" + tid + '\'' +
                ", tname='" + tname + '\'' +
                ", userid='" + userid + '\'' +
                ", aid='" + aid + '\'' +
                ", aname='" + aname + '\'' +
                ", createdtime='" + createdtime + '\'' +
                ", Longitude='" + Longitude + '\'' +
                ", Latitude='" + Latitude + '\'' +
                ", Families='" + Families + '\'' +
                ", Population='" + Population + '\'' +
                ", TwoOverlayMode='" + TwoOverlayMode + '\'' +
                ", TwoTransmitFrequency='" + TwoTransmitFrequency + '\'' +
                ", TwoOverlayNumber=" + TwoOverlayNumber +
                ", TwoOverlayFamiles=" + TwoOverlayFamiles +
                ", TwoOverlayPopulations=" + TwoOverlayPopulations +
                ", PowerConsumption=" + PowerConsumption +
                ", OtherTechnology='" + OtherTechnology + '\'' +
                ", DrillingPeriod='" + DrillingPeriod + '\'' +
                ", StandbyBattery='" + StandbyBattery + '\'' +
                ", PlaybacksMode='" + PlaybacksMode + '\'' +
                ", MaintenanceFunds='" + MaintenanceFunds + '\'' +
                ", Remark='" + Remark + '\'' +
                ", Supervisionunit='" + Supervisionunit + '\'' +
                ", Accountabilityunit='" + Accountabilityunit + '\'' +
                '}';
    }
}
