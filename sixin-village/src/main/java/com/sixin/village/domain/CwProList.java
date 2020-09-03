package com.sixin.village.domain;

import java.util.List;

/**
 * 编辑节目播出单的传入参数
 *
 * @author 黄凤芝
 * @date 2020-1-10
 */
public class CwProList {
    /** 用户ID */
    private String userid;
    /** 播放日期 */
    private String broaddate;
    /** 终端编号集合 */
    private List<String> terminalsID;
    /** 节目编号 */
    private String pid;
    /** 操作记录 */
    private String ptp;
    /** 节目文件 */
    private String fN;
    /** 播放时间 */
    private String bt;
    /** 播放时长，只有电台转播有 */
    private String broadtime;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBroaddate() {
        return broaddate;
    }

    public void setBroaddate(String broaddate) {
        this.broaddate = broaddate;
    }

    public List<String> getTerminalsID() {
        return terminalsID;
    }

    public void setTerminalsID(List<String> terminalsID) {
        this.terminalsID = terminalsID;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPtp() {
        return ptp;
    }

    public void setPtp(String ptp) {
        this.ptp = ptp;
    }

    public String getfN() {
        return fN;
    }

    public void setfN(String fN) {
        this.fN = fN;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getBroadtime() {
        return broadtime;
    }

    public void setBroadtime(String broadtime) {
        this.broadtime = broadtime;
    }
}
