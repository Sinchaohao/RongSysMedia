package com.sixin.broad.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @program: RongSys
 * @description:
 * @开发人员：王豪杰
 * @开发单位：湖南农业大学物联网工程专业
 * @create: 2020-04-01 14:19
 */
public class LedList extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Integer id;
    /** 终端号 */
    @Excel(name = "终端号")
    private String imei;
    /** 内容 */
    @Excel(name = "内容")
    private String content;
    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startdate;
    /** 结束时间 */
    @Excel(name = "结束时间")
    private String enddate;
    /** 创建时间 */
    private String createdate;
    /** 特效*/
    @Excel(name = "特效")
    private String effect;
    /** 颜色 */
    @Excel(name = "颜色")
    private String color;
    /** 管理用户编号*/
    @Excel(name = "管理用户编号")
    private String uid;
    /** 存放地址编号 */
    @Excel(name="存放地址编号")
    private Long deptid;
    /** 设备状态 */
    @Excel(name="设备状态")
    private String status;
    /** 备注说明 */
    @Excel(name = "备注")
    private String demo;
    /** 信息状态  0-无效,1-有效 */
    @Excel(name = "是否有效")
    private String remark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", id)
                .append("imei", imei)
                .append("content", content)
                .append("startdate", startdate)
                .append("enddate", enddate)
                .append("createdate", createdate)
                .append("effect", effect)
                .append("color", color)
                .append("uid", uid)
                .append("deptid", deptid)
                .append("status", status)
                .append("demo", demo)
                .append("remark", remark)
                .toString();
    }
}
