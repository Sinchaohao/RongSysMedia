package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;
import java.util.Date;

/**
 * 工作员信息表staff
 *
 * @author 薛莎莎
 * @date 2019-11-25
 */
public class Staff extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /*工作人员编号*/
    private String stid;

    /*用户编号*/
    private String userid;

    /*地域编号*/
    private String aid;

    /*姓名*/
    private String username;

    /*年龄*/
    private int age;

    /*性别*/
    private String sex;

    /*籍贯*/
    private String nativeplace;

    /*家庭地址*/
    private String address;

    /*民族*/
    private String nation;

    /*政治面貌*/
    private String politics;

    /*文化程度*/
    private String edulevel;

    /*身份证号码*/
    private String idcard;

    /*联系方式*/
    private String phone;

    /*职业*/
    private String job;

    /*工作单位*/
    private String employeeunit;

    /*获奖情况*/
    private String winaward;

    /*处分情况*/
    private String punish;

    /*身份证照片*/
    private String idphoto;

    /*是否有效*/
    private Integer isuse;

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getEdulevel() {
        return edulevel;
    }

    public void setEdulevel(String edulevel) {
        this.edulevel = edulevel;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmployeeunit() {
        return employeeunit;
    }

    public void setEmployeeunit(String employeeunit) {
        this.employeeunit = employeeunit;
    }

    public String getWinaward() {
        return winaward;
    }

    public void setWinaward(String winaward) {
        this.winaward = winaward;
    }

    public String getPunish() {
        return punish;
    }

    public void setPunish(String punish) {
        this.punish = punish;
    }

    public String getIdphoto() {
        return idphoto;
    }

    public void setIdphoto(String idphoto) {
        this.idphoto = idphoto;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "stid='" + stid + '\'' +
                ", userid='" + userid + '\'' +
                ", aid='" + aid + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", nativeplace='" + nativeplace + '\'' +
                ", address='" + address + '\'' +
                ", nation='" + nation + '\'' +
                ", politics='" + politics + '\'' +
                ", edulevel='" + edulevel + '\'' +
                ", idcard='" + idcard + '\'' +
                ", phone='" + phone + '\'' +
                ", job='" + job + '\'' +
                ", employeeunit='" + employeeunit + '\'' +
                ", winaward='" + winaward + '\'' +
                ", punish='" + punish + '\'' +
                ", idphoto='" + idphoto + '\'' +
                ", isuse=" + isuse +
                '}';
    }
}
