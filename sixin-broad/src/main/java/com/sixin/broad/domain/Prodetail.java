package com.sixin.broad.domain;

/**
 * @author 张超 teavamc
 * @Description: 节目汇总
 * @ClassName Prodetail
 * @date 2019/3/2 12:11
 **/
public class Prodetail {

    private String fid;
    private String fname;
    private String userid;
    private String uname;
    private Boolean isreply;
    private String replytime;
    private String recordman;
    private String remark;
    private String aid;

    public String getFid() {
        return fid;
    }
    public void setFid(String fid) {
        this.fid = fid;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public Boolean getIsreply() {
        return isreply;
    }
    public void setIsreply(Boolean isreply) {
        this.isreply = isreply;
    }
    public String getReplytime() {
        return replytime;
    }
    public void setReplytime(String replytime) {
        this.replytime = replytime;
    }
    public String getRecordman() {
        return recordman;
    }
    public void setRecordman(String recordman) {
        this.recordman = recordman;
    }
    public String getAid() {
        return aid;
    }
    public void setAid(String aid) {
        this.aid = aid;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
