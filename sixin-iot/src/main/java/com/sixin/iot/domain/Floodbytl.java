package com.sixin.iot.domain;

import com.sixin.common.base.BaseEntity;

public class Floodbytl extends BaseEntity {
    private Integer limit;
    private String begintime;
    private String endtime;
    private String rid;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }


    @Override
    public String toString() {
        return "Floodbytl{" +
                "limit=" + limit +
                ", begintime='" + begintime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", rid='" + rid + '\'' +
                '}';
    }
}
