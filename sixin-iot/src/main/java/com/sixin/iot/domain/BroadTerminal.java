package com.sixin.iot.domain;

/**
 * @param
 * @Description:
 * @Return:
 * @Author: 蔡文静
 * @单位：湖南农业大学物联网工程专业
 * @Date:
 * @开发版本：综合练习VO.1
 */
public class BroadTerminal {
    private int telid;//编号
    private String tid;//终端IMEI编号
    private String tel;//电话号码
    private String telperson;//用户名
    private boolean isuse;//是否使用
    public Integer getTelid() {
        return telid;
    }
    public void setTelid(int telid) {
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
    public boolean getIsuse() {
        return isuse;
    }
    public void setIsuse(boolean isuse) {
        this.isuse = isuse;
    }

    public String getTelperson() {
        return telperson;
    }

    public void setTelperson(String telperson) {
        this.telperson = telperson;
    }
}
