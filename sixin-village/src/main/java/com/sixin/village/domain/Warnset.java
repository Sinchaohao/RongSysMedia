package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
public class Warnset {

    private Long id;
    @Excel(name = "地址编号")
    private Long deptId;
    @Excel(name = "报警类型")
    private String wtype;
    @Excel(name = "最大值")
    private float mvalue;
    @Excel(name = "最小值")
    private float svalue;
    @Excel(name = "报警信息1")
    private String warn1;
    @Excel(name = "报警信息2")
    private String warn2;
    @Excel(name = "报警优先级")
    private int wrating;
    @Excel(name = "是否有效")
    private int remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getWtype() {
        return wtype;
    }

    public void setWtype(String wtype) {
        this.wtype = wtype;
    }

    public float getMvalue() {
        return mvalue;
    }

    public void setMvalue(float mvalue) {
        this.mvalue = mvalue;
    }

    public float getSvalue() {
        return svalue;
    }

    public void setSvalue(float svalue) {
        this.svalue = svalue;
    }

    public String getWarn1() {
        return warn1;
    }

    public void setWarn1(String warn1) {
        this.warn1 = warn1;
    }

    public String getWarn2() {
        return warn2;
    }

    public void setWarn2(String warn2) {
        this.warn2 = warn2;
    }

    public int getWrating() {
        return wrating;
    }

    public void setWrating(int wrating) {
        this.wrating = wrating;
    }

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }
}
