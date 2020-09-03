package com.sixin.broad.domain;

import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 短信播出端
 *
 * @author 周博
 * @date 2019-03-28
 */
public class BroadMessage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 地域ID
     */
    private String aid;
    /**
     * 父地域ID
     */
    private String parentaid;
    /**
     * 地域名称
     */
    private String aname;
    /**
     * 备注
     */
    private String note;
    /**
     * 终端手机号码
     */
    private String phone;
    /**
     * 终端标识
     */
    private String isDev;

    public String getIsDev() {
        return isDev;
    }

    public void setIsDev(String isDev) {
        this.isDev = isDev;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAid() {
        return aid;
    }

    public void setParentaid(String parentaid) {
        this.parentaid = parentaid;
    }

    public String getParentaid() {
        return parentaid;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAname() {
        return aname;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("aid", getAid())
                .append("parentaid", getParentaid())
                .append("aname", getAname())
                .append("note", getNote())
                .append("phone", getPhone())
                .toString();
    }
}
