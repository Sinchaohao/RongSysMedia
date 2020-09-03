package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by ASUS on 2019/8/11.
 * @author cx
 */
public class ProListen {
    private static final long serialVersionUID = 1L;
    private String recunit;


    /** 节目申请-节目ID */
    private Integer paid;
    /** 节目名称 */
    private String pname;
    /** 用户ID */
    private String userid;
    /** 录制要求 */
    private String requires;
    /** 是否紧急 */
    private Boolean isemergency;
    /** 时间限制要求 */
    private String timelimit;
    /** 要求文稿名 */
    private String filename;
    /** 要求文稿路径 */
    private String fileurl;
    /** 申请提交时间 */
    private String submittime;
    /** 是否撤回 */
    private Boolean iscancel;
    /** 管理员是否阅读 */
    private Boolean isread;
    /** 是否回复 */
    private Boolean isreply;
    /** 回复日期 */
    private String replytime;
    /** 录音人员 */
    private String replyperson;
    /** 回复上传的节目编号 */
    private Integer replyprogramid;
    /** 是否审听 */
    private Boolean islisten;
    /** 审听不通过原因 */
    private String nopassreason;
    /** 是否审听通过 */
    private Boolean listenpass;
    /** 审听未通过次数 */
    private Integer nopasstimes;
    /** 节目id */
    private int fid;
    /** 节目源 */
    private String urls;
    /** 文件名称 */
    private String fname;

    private String profilename;

    /** 用户名称 */
    private String uname;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRecunit() {
        return recunit;
    }

    public void setRecunit(String recunit) {
        this.recunit = recunit;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRequires() {
        return requires;
    }

    public void setRequires(String requires) {
        this.requires = requires;
    }

    public Boolean getIsemergency() {
        return isemergency;
    }

    public void setIsemergency(Boolean isemergency) {
        this.isemergency = isemergency;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getSubmittime() {
        return submittime;
    }

    public void setSubmittime(String submittime) {
        this.submittime = submittime;
    }

    public Boolean getIscancel() {
        return iscancel;
    }

    public void setIscancel(Boolean iscancel) {
        this.iscancel = iscancel;
    }

    public Boolean getIsread() {
        return isread;
    }

    public void setIsread(Boolean isread) {
        this.isread = isread;
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

    public String getReplyperson() {
        return replyperson;
    }

    public void setReplyperson(String replyperson) {
        this.replyperson = replyperson;
    }

    public Integer getReplyprogramid() {
        return replyprogramid;
    }

    public void setReplyprogramid(Integer replyprogramid) {
        this.replyprogramid = replyprogramid;
    }

    public Boolean getIslisten() {
        return islisten;
    }

    public void setIslisten(Boolean islisten) {
        this.islisten = islisten;
    }

    public String getNopassreason() {
        return nopassreason;
    }

    public void setNopassreason(String nopassreason) {
        this.nopassreason = nopassreason;
    }

    public Boolean getListenpass() {
        return listenpass;
    }

    public void setListenpass(Boolean listenpass) {
        this.listenpass = listenpass;
    }

    public Integer getNopasstimes() {
        return nopasstimes;
    }

    public void setNopasstimes(Integer nopasstimes) {
        this.nopasstimes = nopasstimes;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getProfilename() {
        return profilename;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "ProListen{" +
                "recunit='" + recunit + '\'' +
                ", paid=" + paid +
                ", pname='" + pname + '\'' +
                ", userid='" + userid + '\'' +
                ", requires='" + requires + '\'' +
                ", isemergency=" + isemergency +
                ", timelimit='" + timelimit + '\'' +
                ", filename='" + filename + '\'' +
                ", fileurl='" + fileurl + '\'' +
                ", submittime='" + submittime + '\'' +
                ", iscancel=" + iscancel +
                ", isread=" + isread +
                ", isreply=" + isreply +
                ", replytime='" + replytime + '\'' +
                ", replyperson='" + replyperson + '\'' +
                ", replyprogramid=" + replyprogramid +
                ", islisten=" + islisten +
                ", nopassreason='" + nopassreason + '\'' +
                ", listenpass=" + listenpass +
                ", nopasstimes=" + nopasstimes +
                ", fid=" + fid +
                ", urls='" + urls + '\'' +
                ", fname='" + fname + '\'' +
                ", profilename='" + profilename + '\'' +
                ", uname='" + uname + '\'' +
                '}';
    }
}
