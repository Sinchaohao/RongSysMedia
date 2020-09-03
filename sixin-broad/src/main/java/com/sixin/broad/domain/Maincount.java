package com.sixin.broad.domain;

import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 终端维护记录统计 terminal_maintain
 * 
 * @author 张鸿权
 * @date 2019-04-21
 */
public class Maincount extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 终端维护记录-维护ID */
	private Integer tmid;
	/** 终端ID */
	private String tid;
	/** 故障现象 */
	private String fault;
	/** 维护人员 */
	private String mstaff;
	/** 维护时间 */
	private String createtime;
	/** 备注 */
	private String remark;
	/** 终端名称 */
	private String tname;
	/** 终端号码 */
	private String phone;
	/** 终端地址 */
	private String address;
	/** 网络类型 */
	private String nettype;
	/** 所属分组 */
	private String aname;
	/** 所属用户 */
	private String uname;
	/** 所属分组编号 */
	private String aid;

	public String getMnum() {
		return mnum;
	}

	public void setMnum(String mnum) {
		this.mnum = mnum;
	}

	/** 维修次数 */

	private String mnum;

	public void setTmid(Integer tmid) 
	{
		this.tmid = tmid;
	}

	public Integer getTmid() 
	{
		return tmid;
	}
	public void setTid(String tid) 
	{
		this.tid = tid;
	}

	public String getTid() 
	{
		return tid;
	}
	public void setFault(String fault) 
	{
		this.fault = fault;
	}

	public String getFault() 
	{
		return fault;
	}
	public void setMstaff(String mstaff) 
	{
		this.mstaff = mstaff;
	}

	public String getMstaff() 
	{
		return mstaff;
	}
	public void setCreatetime(String createtime) 
	{
		this.createtime = createtime;
	}

	public String getCreatetime() 
	{
		return createtime;
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

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNettype() {
		return nettype;
	}

	public void setNettype(String nettype) {
		this.nettype = nettype;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}


	@Override
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tmid", getTmid())
            .append("tid", getTid())
            .append("fault", getFault())
            .append("mstaff", getMstaff())
            .append("createtime", getCreatetime())
            .append("remark", getRemark())
			.append("uname", getUname())
			.append("aname", getAname())
			.append("tname", getTname())
			.append("aid", getAid())
			.append("nettype", getNettype())
			.append("address", getAddress())
			.append("phone", getPhone())
			.append("mnum", getMnum())
			.toString();
    }
}
