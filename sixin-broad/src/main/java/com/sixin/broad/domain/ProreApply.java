package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 节目申请表 prore_apply
 * 
 * @author 张超
 * @date 2019-03-02
 */
public class ProreApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String recunit;


	/** 节目申请-节目ID */
	private Integer paid;
	/** 节目名称 */
	private String pname;
	/** 用户ID */
	private String userid;
	/** 录制要求 */
	private String requires;
	/** 是否紧急 */
	private String isemergency;
	/** 时间限制要求 */
	private String timelimit;
	/** 要求文稿名 */
	private String filename;
	/** 要求文稿路径 */
	private String fileurl;
	/** 申请提交时间 */
	private String submittime;
	/** 是否撤回 */
	private Boolean iscancel;
	/** 管理员是否阅读 */
	private Boolean isread;
	/** 是否回复 */
	private Boolean isreply;
	/** 回复日期 */
	private String replytime;
	/** 录音人员 */
	private String replyperson;
	/** 回复上传的节目编号 */
	private Integer replyprogramid;
	/** 是否审听 */
	private Boolean islisten;
	/** 审听不通过原因 */
	private String nopassreason;
	/** 是否审听通过 */
	private Boolean listenpass;
	/** 审听未通过次数 */
	private Integer nopasstimes;

	public ProreApply() {
	}

	public ProreApply(Integer paid, Boolean isreply, String replyperson,int replyprogramid) {
		this.paid = paid;
		this.isreply = isreply;
		this.replyperson = replyperson;
		this.replyprogramid = replyprogramid;
	}

	public ProreApply(String pname, String userid, String requires, String isemergency, String timelimit, String fileurl) {
		this.pname = pname;
		this.userid = userid;
		this.requires = requires;
		this.isemergency = isemergency;
		this.timelimit = timelimit;
		this.fileurl = fileurl;
	}

	public String getIsemergency() {
		return isemergency;
	}

	public void setIsemergency(String isemergency) {
		this.isemergency = isemergency;
	}

	public String getRecunit() {
		return recunit;
	}
	public void setRecunit(String recunit) {
		this.recunit = recunit;
	}

	public void setPaid(Integer paid) 
	{
		this.paid = paid;
	}

	public Integer getPaid() 
	{
		return paid;
	}
	public void setPname(String pname) 
	{
		this.pname = pname;
	}

	public String getPname() 
	{
		return pname;
	}
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}

	public String getUserid() 
	{
		return userid;
	}
	public void setRequires(String requires) 
	{
		this.requires = requires;
	}

	public String getRequires() 
	{
		return requires;
	}



	public void setTimelimit(String timelimit) 
	{
		this.timelimit = timelimit;
	}

	public String getTimelimit() 
	{
		return timelimit;
	}
	public void setFilename(String filename) 
	{
		this.filename = filename;
	}

	public String getFilename() 
	{
		return filename;
	}
	public void setFileurl(String fileurl) 
	{
		this.fileurl = fileurl;
	}

	public String getFileurl() 
	{
		return fileurl;
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
		this.iscancel = iscancel;
	}

	public Boolean getIscancel() 
	{
		return iscancel;
	}
	public void setIsread(Boolean isread) 
	{
		this.isread = isread;
	}

	public Boolean getIsread() 
	{
		return isread;
	}
	public void setIsreply(Boolean isreply) 
	{
		this.isreply = isreply;
	}

	public Boolean getIsreply() 
	{
		return isreply;
	}
	public void setReplytime(String replytime) 
	{
		this.replytime = replytime;
	}

	public String getReplytime() 
	{
		return replytime;
	}
	public void setReplyperson(String replyperson) 
	{
		this.replyperson = replyperson;
	}

	public String getReplyperson() 
	{
		return replyperson;
	}
	public void setReplyprogramid(Integer replyprogramid) 
	{
		this.replyprogramid = replyprogramid;
	}

	public Integer getReplyprogramid() 
	{
		return replyprogramid;
	}
	public void setIslisten(Boolean islisten) 
	{
		this.islisten = islisten;
	}

	public Boolean getIslisten() 
	{
		return islisten;
	}
	public void setNopassreason(String nopassreason) 
	{
		this.nopassreason = nopassreason;
	}

	public String getNopassreason() 
	{
		return nopassreason;
	}
	public void setListenpass(Boolean listenpass) 
	{
		this.listenpass = listenpass;
	}

	public Boolean getListenpass() 
	{
		return listenpass;
	}
	public void setNopasstimes(Integer nopasstimes) 
	{
		this.nopasstimes = nopasstimes;
	}

	public Integer getNopasstimes() 
	{
		return nopasstimes;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paid", getPaid())
            .append("pname", getPname())
            .append("userid", getUserid())
            .append("requires", getRequires())
            .append("timelimit", getTimelimit())
            .append("filename", getFilename())
            .append("fileurl", getFileurl())
            .append("submittime", getSubmittime())
            .append("iscancel", getIscancel())
            .append("isread", getIsread())
            .append("isreply", getIsreply())
            .append("replytime", getReplytime())
            .append("replyperson", getReplyperson())
            .append("replyprogramid", getReplyprogramid())
            .append("islisten", getIslisten())
            .append("nopassreason", getNopassreason())
            .append("listenpass", getListenpass())
            .append("nopasstimes", getNopasstimes())
            .toString();
    }
}
