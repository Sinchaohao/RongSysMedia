package com.sixin.iot.domain;

import com.sixin.common.base.BaseEntity;

public class LEDgetPa extends BaseEntity {
    private int id;
    private Integer limit;
    private String begintime;
    private String endtime;

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "LEDgetPa{" +
                "id='" + id + '\'' +
                ", limit=" + limit +
                ", begintime='" + begintime + '\'' +
                ", endtime='" + endtime + '\'' +
                '}';
    }
}
