package com.sixin.broad.domain;

import com.sixin.common.base.BaseEntity;

/**
 * @program: dyq_RongSys
 * @description: 短信表
 * @author: 戴誉琪
 * @create: 2019-01-14 15:25
 **/
public class Textmessages extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**电话id*/
    private String telid;
    /**终端id*/
    private String tid;
    /** 电话号码*/
    private String tel;
    /** 电话号码所属人*/
    private String telperson;
    /** 是否可用*/
    private int isuse;


    @Override
    public String toString() {
        return "Textmessages{" +
                "telid='" + telid + '\'' +
                ", tid='" + tid + '\'' +
                ", tel='" + tel + '\'' +
                ", telpersion='" + telperson + '\'' +
                ", isuse=" + isuse +
                '}';
    }

    public Textmessages(String telid, String tid, String tel,
                        String telperson,
                        int isuse) {
        this.telid = telid;
        this.tid = tid;
        this.tel = tel;
        this.telperson = telperson;
        this.isuse = isuse;
    }

    public Textmessages() {
        super();
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTelid() {
        return telid;
    }

    public void setTelid(String telid) {
        this.telid = telid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTelperson() {
        return telperson;
    }

    public void setTelperson(String telperson) {
        this.telperson = telperson;
    }

    public int getIsuse() {
        return isuse;
    }

    public void setIsuse(int isuse) {
        this.isuse = isuse;
    }
}
