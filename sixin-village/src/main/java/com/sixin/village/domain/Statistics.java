package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
public class Statistics {
    @Excel(name = "参数编号")
    private String valueid;
    @Excel(name = "参数名称")
    private String valuename;
    @Excel(name = "是否有效")
    private int issingleselection;

    public String getValueid() {
        return valueid;
    }

    public void setValueid(String valueid) {
        this.valueid = valueid;
    }

    public String getValuename() {
        return valuename;
    }

    public void setValuename(String valuename) {
        this.valuename = valuename;
    }

    public int getIssingleselection() {
        return issingleselection;
    }

    public void setIssingleselection(int issingleselection) {
        this.issingleselection = issingleselection;
    }
}
