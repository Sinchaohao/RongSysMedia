package com.sixin.broad.domain;

/**
 * 特种节目管理
 *
 * @author 周博
 * @date 2019-03-27
 */
public class ProSpec {
    private String fid;
    private String fname;
    private String filename;
    private String flenth;
    private String fsize;
    private String uname;
    private String createdtime;

    /*节目上传路径*/
    private String urls;

    /** 操作（所属）用户编号 */
    private String userid;

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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFlenth() {
        return flenth;
    }

    public void setFlenth(String flenth) {
        this.flenth = flenth;
    }

    public String getFsize() {
        return fsize;
    }

    public void setFsize(String fsize) {
        this.fsize = fsize;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProSpec").append('[')
                .append("fid=")
                .append(fid)
                .append(",fname=")
                .append(fname)
                .append(",filename=")
                .append(filename)
                .append(",flenth=")
                .append(flenth)
                .append(",fsize=")
                .append(fsize)
                .append(",uname=")
                .append(uname)
                .append(",createdtime=")
                .append(createdtime)
                .append(",urls=")
                .append(urls)
                .append(",userid=")
                .append(userid)
                .append(']');
        return sb.toString();
    }
}
