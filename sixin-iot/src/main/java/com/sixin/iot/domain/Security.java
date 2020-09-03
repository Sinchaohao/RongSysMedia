package com.sixin.iot.domain;

import com.sixin.common.annotation.Excel;

public class Security {
    @Excel(name="编号")
    private String id;
    @Excel(name="安防类型")
    private String stype;
    @Excel(name="存放地址编号")
    private Integer dept_id;
    @Excel(name="设备编号")
    private String sno;
    @Excel(name="产品型号名称")
    private String sname;
    @Excel(name="生产企业")
    private String producer;
    @Excel(name="生产时间")
    private String prodtime;
    @Excel(name="采购价格")
    private  String price;
    @Excel(name="有效时间")
    private String endtime;
    @Excel(name="安装时间")
    private String installtime;
    @Excel(name="安装人员")
    private String installer;
    @Excel(name="备注")
    private String demo;
    @Excel(name="设备状态")
    private String delflag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProdtime() {
        return prodtime;
    }

    public void setProdtime(String prodtime) {
        this.prodtime = prodtime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getInstalltime() {
        return installtime;
    }

    public void setInstalltime(String installtime) {
        this.installtime = installtime;
    }

    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installer) {
        this.installer = installer;
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
        return "Security{" +
                "id='" + id + '\'' +
                ", stype='" + stype + '\'' +
                ", dept_id=" + dept_id +
                ", sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", producer='" + producer + '\'' +
                ", prodtime='" + prodtime + '\'' +
                ", price='" + price + '\'' +
                ", endtime='" + endtime + '\'' +
                ", installtime='" + installtime + '\'' +
                ", installer='" + installer + '\'' +
                ", demo='" + demo + '\'' +
                ", delflag='" + delflag + '\'' +
                '}';
    }
}