package com.sixin.iot.domain;

import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @开发人员：周宇滔
 * @开发单位：湖南农业大学物联网工程专业
 */

public class MetCount extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private String id;
    private String rname;
    private String rtime;
    private String rdata;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getRdata() {
        return rdata;
    }

    public void setRdata(String rdata) {
        this.rdata = rdata;
    }


    @Override
    public String toString() {
        return "MetCount{" +
                "id='" + id + '\'' +
                ", rname='" + rname + '\'' +
                ", rtime='" + rtime + '\'' +
                ", rdata='" + rdata + '\'' +
                '}';
    }
}
