package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;

/**
 * 特殊人员监控表sys_specialperson
 *
 * @author 薛莎莎
 * @date 2019-12-15
 */
public class Specialperson extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /*编号*/
    private Integer id;

    /*人员编号*/
    private String pid;

    /*人员姓名*/
    private String pname;

    /*人员电话*/
    private String phone;

    /*信号类型*/
    private String ptype;

    /*基站编码*/
    private String stationcode;

    /*辐射半径*/
    private String redius;

    /*人员登记地址*/
    private String address;

    /*开始时间*/
    private String startdate;

    /*结束时间*/
    private String enddate;

    /*备注说明*/
    private String demo;

    /*是否有效0-无效,1-有效*/
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getRedius() {
        return redius;
    }

    public void setRedius(String redius) {
        this.redius = redius;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "specialperson{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", phone='" + phone + '\'' +
                ", ptype='" + ptype + '\'' +
                ", stationcode='" + stationcode + '\'' +
                ", redius='" + redius + '\'' +
                ", address='" + address + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", demo='" + demo + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
