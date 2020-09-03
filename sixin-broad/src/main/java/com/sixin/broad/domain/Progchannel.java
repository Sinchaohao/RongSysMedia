package com.sixin.broad.domain;

/**
 * @author 张超 teavamc
 * @Description: 转播频道
 * @ClassName Progchannel
 * @date 2019/3/2 12:12
 **/
public class Progchannel {

    private String cid;//编号
    private String cname;//频道名称
    private String userid;//操作用户
    private String frequencies;//fm地址
    private String remark;//备注
    private String pronote;
    public String getCid() {
        return cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getCname() {
        return cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getFrequencies() {
        return frequencies;
    }
    public void setFrequencies(String frequencies) {
        this.frequencies = frequencies;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getPronote() {
        return pronote;
    }
    public void setPronote(String pronote) {
        this.pronote = pronote;
    }

}
