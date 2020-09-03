package com.sixin.iot.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.awt.*;
import java.util.Date;

public class LED extends BaseEntity {
    @Excel(name = "LED显示编号")
    private Long id;
    @Excel(name = "终端号")
    private String imei;
    @Excel(name = "管理用户编号")
    private String uid;
    @Excel(name = "地址编号")
    private  Long deptid;
    @Excel(name = "LED显示信息")
    private String content;
    @Excel(name = "开始显示时间")
    private Date startdate;
    @Excel(name = "显示结束时间")
    private Date enddate;
    @Excel(name = "设备状态")
    private String status;
    @Excel(name = "创建时间")
    private Date createdate;
    @Excel(name = "特效")
    private String effect;
    @Excel(name = "颜色")
    private String color;
    @Excel(name = "备注")
    private String demo;
    @Excel(name = "是否有效",readConverterExp = "N=否,Y=是")
    private String remark;
    @Excel(name="录入人")
    private String user_name;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("imei", getImei())
                .append("color", getColor())
                .append("content", getContent())
                .append("createdate", getCreatedate())
                .append("effect", getEffect())
                .append("startdate", getStartdate())
                .append("enddate", getEnddate())
                .append("deptid", getDeptid())
                .append("uid", getUid())
                .append("demo", getDemo())
                .append("status", getStatus())
                .append("remark", getRemark())
                .append("user_name", getUser_name())
                .toString();
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

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
