package com.sixin.broad.domain;

/**
 * Created by ASUS on 2019/3/20.
 * @author 陈霞
 * @function 统计终端故障时间与故障终端号的坐标关系
 */
public class TerminalCount {
    /*终端号*/
    private int tmid;
    /*终端数量*/
    private Integer tcount;
    /*终端故障原因*/
    private String time;

    private Long deptId;

    public int getTmid() {
        return tmid;
    }

    public void setTmid(int tmid) {
        this.tmid = tmid;
    }

    public Integer getTcount() {
        return tcount;
    }

    public void setTcount(Integer tcount) {
        this.tcount = tcount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "TerminalCount{" +
                "tmid=" + tmid +
                ", tcount=" + tcount +
                ", time='" + time + '\'' +
                ", deptId='" + deptId + '\'' +
                '}';
    }
}
