package com.sixin.village.domain;

/**
 * 党员数据统计的实体
 *
 * @author 张超
 * @date 2019-01-25
 */
public class Pmcount {
    private static final long serialVersionUID = 1L;

    /** 地区编号 */
    private String aid;
    /** 地区分组 */
    private String parea;

    /** 地区党员总数 */
    private int psum;

    /** 男党员 */
    private int man;

    /** 女党员 */
    private int woman;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getParea() {
        return parea;
    }

    public void setParea(String parea) {
        this.parea = parea;
    }

    public int getPsum() {
        return psum;
    }

    public void setPsum(int psum) {
        this.psum = psum;
    }

    public int getMan() {
        return man;
    }

    public void setMan(int man) {
        this.man = man;
    }

    public int getWoman() {
        return woman;
    }

    public void setWoman(int woman) {
        this.woman = woman;
    }
}
