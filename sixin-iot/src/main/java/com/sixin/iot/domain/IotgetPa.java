package com.sixin.iot.domain;

public class IotgetPa {
    private String IMEI;
    private Integer limit;
    private String begintime;
    private String endtime;

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
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
        return "IotgetPa{" +
                "IMEI='" + IMEI + '\'' +
                ", limit=" + limit +
                ", begintime='" + begintime + '\'' +
                ", endtime='" + endtime + '\'' +
                '}';
    }
}
