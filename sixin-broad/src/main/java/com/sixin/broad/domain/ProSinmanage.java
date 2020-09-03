package com.sixin.broad.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;

/**
 * 节目播出单表 pro_sinmanage
 * 
 * @author 张超
 * @date 2019-03-02
 */
public class ProSinmanage extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 节目播出单IDSin */
	@Excel(name = "节目播出单IDSin")
	private String sfid;
	/** 节目播出单类别 */
	@Excel(name = "节目播出单类别")
	private String scategory;
	/*提交时间*/
	private String submittime;
	/*节目名称*/
	private String pname;

	/** 创建时间 */
	@Excel(name = "创建时间")
	private String createtime;
	/** 播出日期 */
	@Excel(name = "播出日期")
	private String broaddate;
	/** 操作（所属）用户 */
	private String userid;
	/** 操作（所属）用户名 */
	private String username;
	/** 操作（所属）用户名 */
	@Excel(name = "操作（所属）用户名")
	private String uname;
	/** 备注 */
	@Excel(name = "备注")
	private String remark;
	/** 紧急播出单播放次数 */
	private String broadtimes;

	private String tid;

	private String tname;

	private String aname;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public void setSfid(String sfid)
	{
		this.sfid = sfid;
	}

	public String getSfid()
	{
		return sfid;
	}
	public void setScategory(String scategory)
	{
		this.scategory = scategory;
	}

	public String getScategory()
	{
		return scategory;
	}
	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
	}

	public String getCreatetime()
	{
		return createtime;
	}
	public void setBroaddate(String broaddate)
	{
		this.broaddate = broaddate;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getBroaddate()
	{
		return broaddate;
	}
	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getUserid()
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

	@Override
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Override
	public String getRemark()
	{
		return remark;
	}

	public void setBroadtimes(String broadtimes)
	{
		this.broadtimes = broadtimes;
	}

	public String getBroadtimes()
	{
		return broadtimes;
	}

	public String getSubmittime() {
		return submittime;
	}

	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	@Override
	public String toString() {
		return "ProSinmanage{" +
				"sfid='" + sfid + '\'' +
				", scategory='" + scategory + '\'' +
				", submittime='" + submittime + '\'' +
				", pname='" + pname + '\'' +
				", createtime='" + createtime + '\'' +
				", broaddate='" + broaddate + '\'' +
				", userid=" + userid +
				", username='" + username + '\'' +
				", uname='" + uname + '\'' +
				", remark='" + remark + '\'' +
				", broadtimes='" + broadtimes + '\'' +
				'}';
	}
}
