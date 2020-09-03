package com.sixin.broad.domain;

/**
 * @author 张超 teavamc
 * @Description: 节目播出单的节目明细管理
 * @ClassName Program_List
 * @date 2019/3/2 12:13
 **/
public class Program_List {

    private String pid;//播出单编号
    private String ptp;//播出节目类别
    private String fn;//节目文件
    private String fid;//节目编号
    private String bt;//开始时间
    private String broadtime;//播放时间
    private String remark;//备注

    private Program program;//文件转播节目信息
    private Progchannel prochan;//电台转播节目信息

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

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String getBroadtime() {
        return broadtime;
    }

    public void setBroadtime(String broadtime) {
        this.broadtime = broadtime;
    }

    public Progchannel getProchan() {
        return prochan;
    }

    public void setProchan(Progchannel prochan) {
        this.prochan = prochan;
    }

}
