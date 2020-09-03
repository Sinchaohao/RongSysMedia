package com.sixin.broad.domain;

/**
 * @Description: 数量统计$
 * @Param: $
 * @return: $
 * @Author: 戴誉琪
 * @Date: $
 */
public class typenum {
/** 类型数量 */
    private String textnum;
    /** 数量 */
    private  String num;

    /**
     电话号码
     */
    private String telnumber;

    public String getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }

    public String getTextnum() {
        return textnum;
    }

    public void setTextnum(String textnum) {
        this.textnum = textnum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public typenum(String textnum, String num, String telnumber) {
        this.textnum = textnum;
        this.num = num;
        this.telnumber = telnumber;
    }

    public typenum() {
    }

    @Override
    public String toString() {
        return "typenum{" +
                "textnum='" + textnum + '\'' +
                ", num='" + num + '\'' +
                ", telnumber='" + telnumber + '\'' +
                '}';
    }
}
