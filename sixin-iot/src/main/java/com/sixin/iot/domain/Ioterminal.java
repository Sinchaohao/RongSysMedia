package com.sixin.iot.domain;

import com.sixin.common.annotation.Excel;

public class Ioterminal {
    private Integer  id;

    private String imei;
    private String ctype;
    @Excel(name = "控制IP")
    private String conip;
    @Excel(name = "采集状态",readConverterExp = "0=正常,1=关闭")
    private String status;
    @Excel(name = "安装时间")
    private String startdate;
    @Excel(name = "更换时间")
    private String exdate;
    @Excel(name = "存放位置编号")
    private Integer deptid;
    @Excel(name = "录入人编号")
    private  String uid;
    @Excel(name = "录入人姓名")
    private  String user_name;
    @Excel(name = "备注")
    private String demo;
    @Excel(name = "设备状态",readConverterExp = "0=开启,1=关闭")
    private String delflag;

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

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
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

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
        return "Ioterminal{" +
                "id=" + id +
                ", imei='" + imei + '\'' +
                ", ctype='" + ctype + '\'' +
                ", conip='" + conip + '\'' +
                ", status='" + status + '\'' +
                ", startdate='" + startdate + '\'' +
                ", exdate='" + exdate + '\'' +
                ", deptid=" + deptid +
                ", uid='" + uid + '\'' +
                ", user_name='" + user_name + '\'' +
                ", demo='" + demo + '\'' +
                ", delflag='" + delflag + '\'' +
                '}';
    }
}
