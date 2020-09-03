package com.sixin.village.domain;

public class Pedulevel {
    private static final long serialVersionUID = 1L;

    /** 地区分组 */
    private String edulevel;

    /** 各学历党员总数 */
    private int psum;

    public String getEdulevel() {
        return edulevel;
    }

    public void setEdulevel(String edulevel) {
        this.edulevel = edulevel;
    }

    public int getPsum() {
        return psum;
    }

    public void setPsum(int psum) {
        this.psum = psum;
    }
}
