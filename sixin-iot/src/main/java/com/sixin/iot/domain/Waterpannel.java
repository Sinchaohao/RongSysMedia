 package com.sixin.iot.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;
 import java.util.Date;

/**
 * 终端运转表 torrent
 *
 * @author 张超
 * @date 2019-03-03
 */

  public class   Waterpannel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String aid;

    private String tem;

    private String ph;

    private String ndata;

    private String pdata;

    private String qdata;

    private String sdata;

    private String fudata;

    private String bacdata;

    private String sampling_by;

    private Date sampling_time;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getNdata() {
        return ndata;
    }

    public void setNdata(String ndata) {
        this.ndata = ndata;
    }

    public String getPdata() {
        return pdata;
    }

    public void setPdata(String pdata) {
        this.pdata = pdata;
    }

    public String getQdata() {
        return qdata;
    }

    public void setQdata(String qdata) {
        this.qdata = qdata;
    }

    public String getSdata() {
        return sdata;
    }

    public void setSdata(String sdata) {
        this.sdata = sdata;
    }

    public String getFudata() {
        return fudata;
    }

    public void setFudata(String fudata) {
        this.fudata = fudata;
    }

    public String getBacdata() {
        return bacdata;
    }

    public void setBacdata(String bacdata) {
        this.bacdata = bacdata;
    }

    public String getSampling_by() {
        return sampling_by;
    }

    public void setSampling_by(String sampling_by) {
        this.sampling_by = sampling_by;
    }

    public Date getSampling_time() {
        return sampling_time;
    }

    public void setSampling_time(Date sampling_time) {
        this.sampling_time = sampling_time;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Waterpannel{" +
                "id=" + id +
                ", aid='" + aid + '\'' +
                ", tem='" + tem + '\'' +
                ", ph='" + ph + '\'' +
                ", ndata='" + ndata + '\'' +
                ", pdata='" + pdata + '\'' +
                ", qdata='" + qdata + '\'' +
                ", sdata='" + sdata + '\'' +
                ", fudata='" + fudata + '\'' +
                ", bacdata='" + bacdata + '\'' +
                ", sampling_by='" + sampling_by + '\'' +
                ", sampling_time=" + sampling_time +
                ", remark='" + remark + '\'' +
                '}';
    }
}