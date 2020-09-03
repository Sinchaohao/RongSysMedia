package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Managementgps {

    private static final long serialVersionUID = 1L;

    /**Tid*/
    private String tid;
    /** 安装点经度 */
    private Double longitude;
    /** 纬度 */
    private Double latitude;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tid", getTid())
                .append("longitude", getLongitude())
                .append("latitude", getLatitude())
                .toString();
    }
}
