package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BroadPersonpush{
    private Integer aid;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    private String phone;
    private Long uid;
    private Long allowid;
    private String uname;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getAllowid() {
        return allowid;
    }

    public void setAllowid(Long allowid) {
        this.allowid = allowid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("phone", getPhone())
                .append("uid", getUid())
                .append("allowid", getAllowid())
                .append("uname", getUname())
                .toString();
    }
}