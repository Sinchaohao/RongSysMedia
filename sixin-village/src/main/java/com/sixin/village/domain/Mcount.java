package com.sixin.village.domain;

/**
 * 村民数据统计的实体
 *
 * @author 张超
 * @date 2019-01-25
 */
public class Mcount {
    private static final long serialVersionUID = 1L;

    /** 地区编号 */
    private String aid;
    /** 地区分组 */
    private String marea;

    /** 村民总数 */
    private int msum;

    /** 男 */
    private int man;

    /** 女 */
    private int woman;

    private int sum2;
    private int sum3;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getMarea() {
        return marea;
    }

    public void setMarea(String marea) {
        this.marea = marea;
    }

    public int getMsum() {
        return msum;
    }

    public void setMsum(int msum) {
        this.msum = msum;
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

    public int getSum2() {
        return sum2;
    }

    public void setSum2(int sum2) {
        this.sum2 = sum2;
    }

    public int getSum3() {
        return sum3;
    }

    public void setSum3(int sum3) {
        this.sum3 = sum3;
    }
}
