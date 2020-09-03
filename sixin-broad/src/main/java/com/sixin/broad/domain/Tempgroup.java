package com.sixin.broad.domain;

/**
 * Created by ASUS on 2019/7/29.
 * @author cx
 */
public class Tempgroup {

    private int tgid;
    private String tgname;
    private String userid;
    private String createtime;
    private String note;
    private Byte isuse;
    private String rightuserid;
    private String tid;
    private String tname;
    private String aname;
    private String uname;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getTgid() {
        return tgid;
    }

    public void setTgid(int tgid) {
        this.tgid = tgid;
    }

    public String getTgname() {
        return tgname;
    }

    public void setTgname(String tgname) {
        this.tgname = tgname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Byte getIsuse() {
        return isuse;
    }

    public void setIsuse(Byte isuse) {
        this.isuse = isuse;
    }

    public String getRightuserid() {
        return rightuserid;
    }

    public void setRightuserid(String rightuserid) {
        this.rightuserid = rightuserid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tempgroup").append('[')
                .append("tgid=")
                .append(tgid)
                .append(",tgname=")
                .append(tgname)
                .append(",userid=")
                .append(userid)
                .append(",createtime=")
                .append(createtime)
                .append(",note=")
                .append(note)
                .append(",isuse=")
                .append(isuse)
                .append(",rightuserid=")
                .append(rightuserid)
                .append(",tid=")
                .append(tid)
                .append(",tname=")
                .append(tname)
                .append(",aname=")
                .append(aname)
                .append(",uname=")
                .append(uname)
                .append(']');
        return sb.toString();
    }
}
