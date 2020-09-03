package com.sixin.broad.domain;

/**
 * @author 张超 teavamc
 * @Description: 终端下载播出单记录表
 * @ClassName TerReceives
 * @date 2019/3/2 21:54
 **/
public class TerReceives {

    private String receiveid;
    private String terminalid;
    private String tname;
    private String programmeid;
    private String createdtime;
    private String issuccess;//下载是否成功
    private String remark;

    private String intertime;//终端交互时间

    private Program_List prolist;//节目列表

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getProgrammeid() {
        return programmeid;
    }

    public void setProgrammeid(String programmeid) {
        this.programmeid = programmeid;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(String issuccess) {
        this.issuccess = issuccess;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIntertime() {
        return intertime;
    }

    public void setIntertime(String intertime) {
        this.intertime = intertime;
    }

    public Program_List getProlist() {
        return prolist;
    }

    public void setProlist(Program_List prolist) {
        this.prolist = prolist;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getReceiveid() {
        return receiveid;
    }

    public void setReceiveid(String receiveid) {
        this.receiveid = receiveid;
    }

}
