package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 节目申请表 ProApplyUser.java
 *
 * @author 张超
 * @date 2019-03-02
 */
public class ProApplyUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 节目申请-节目ID */
    private Integer paid;
    /** 节目名称 */
    private String pname;
    /** 用户名字 */
    private String uname;
    /** 用户ID */
    private Long userid;
    /** 是否紧急 */
    private String isemergency;
    /** 时间限制要求 */
    private String timelimit;
    /** 申请提交时间 */
    private String submittime;
    /** 是否撤回 */
    private String iscancel;
    /** 是否回复 */
    private String isreply;
    /** 是否阅读 */
    private String isread;

    private String requires;

    private String replyperson;

    public String getIsemergency() {
        return isemergency;
    }

    public void setIsemergency(String isemergency) {
        this.isemergency = isemergency;
    }

    public String getReplyperson() {
        return replyperson;
    }

    public void setReplyperson(String replyperson) {
        this.replyperson = replyperson;
    }

    private String replytime;

    public String getReplytime() {
        return replytime;
    }

    public void setReplytime(String replytime) {
        this.replytime = replytime;
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public String getRequires() {
        return requires;
    }

    public void setRequires(String requires) {
        this.requires = requires;
    }

    public void setPaid(Integer paid)
    {
        this.paid = paid;
    }

    public Integer getPaid()
    {
        return paid;
    }
    public void setUserid(Long userid)
    {
        this.userid = userid;
    }

    public Long getUserid()
    {
        return userid;
    }
    public void setUname(String uname)
    {
        this.uname = uname;
    }

    public String getUname()
    {
        return uname;
    }
    public void setPname(String pname)
    {
        this.pname = pname;
    }

    public String getPname()
    {
        return pname;
    }

    public void setTimelimit(String timelimit)
    {
        this.timelimit = timelimit;
    }

    public String getTimelimit()
    {
        return timelimit;
    }
    public void setSubmittime(String submittime)
    {
        this.submittime = submittime;
    }

    public String getSubmittime()
    {
        return submittime;
    }

    public void setIscancel(Boolean iscancel)
    {
        if (iscancel==true){
            this.iscancel = "已撤回";
        }else{
            this.iscancel = "未撤回";
        }
    }

    public String getIscancel()
    {
        return iscancel;
    }
    public void setIsreply(Boolean isreply)
    {
        if (isreply==true){
            this.isreply = "已回复";
        }else{
            this.isreply = "未回复";
        }
    }

    public String getIsreply()
    {
        return isreply;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("paid", getPaid())
                .append("pname", getPname())
                .append("uname", getUname())
                .append("userid", getUserid())
                .append("isemergency", getIsemergency())
                .append("timelimit", getTimelimit())
                .append("submittime", getSubmittime())
                .append("iscancel", getIscancel())
                .append("isreply", getIsreply())
                .toString();
    }
}
