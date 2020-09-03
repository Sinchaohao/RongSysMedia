package com.sixin.village.domain;

/**
 * 授权号码表管理sys_phonemanage
 *
 * @author 薛莎莎
 * @date 2019-12-6
 */

import com.sixin.common.base.BaseEntity;

import java.util.Date;

public class Phonemanage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /*编号*/
    private Integer pid;

    /*授权号码*/
    private String phone;

    /*用户编号*/
    private Integer uid;

    /*用户姓名*/
    private String uname;

    /*权限编码*/
    private String rolecode;

    /*批准人编号*/
    private Integer allowid;

    /*批准人姓名*/
    private String allowname;

    /*首次批准时间*/
    private Date allowdate;

    /*再次批准人编号*/
    private Integer reallowid;

    /*再次批准人姓名*/
    private String reallowname;

    /*批准人证明*/
    private String allowpic;

    /*备注*/
    private String demo;

    /*0-无效,1-有效*/
    private String remark;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public Integer getAllowid() {
        return allowid;
    }

    public void setAllowid(Integer allowid) {
        this.allowid = allowid;
    }

    public String getAllowname() {
        return allowname;
    }

    public void setAllowname(String allowname) {
        this.allowname = allowname;
    }

    public Date getAllowdate() {
        return allowdate;
    }

    public void setAllowdate(Date allowdate) {
        this.allowdate = allowdate;
    }

    public Integer getReallowid() {
        return reallowid;
    }

    public void setReallowid(Integer reallowid) {
        this.reallowid = reallowid;
    }

    public String getReallowname() {
        return reallowname;
    }

    public void setReallowname(String reallowname) {
        this.reallowname = reallowname;
    }

    public String getAllowpic() {
        return allowpic;
    }

    public void setAllowpic(String allowpic) {
        this.allowpic = allowpic;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
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
        return "Phonemanage{" +
                "pid='" + pid + '\'' +
                ", phone='" + phone + '\'' +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", rolecode='" + rolecode + '\'' +
                ", allowid=" + allowid +
                ", allowname='" + allowname + '\'' +
                ", allowdate=" + allowdate +
                ", reallowid=" + reallowid +
                ", reallowname='" + reallowname + '\'' +
                ", allowpic='" + allowpic + '\'' +
                ", demo='" + demo + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
