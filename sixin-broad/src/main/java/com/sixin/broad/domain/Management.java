package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 终端管理表 terminal_management
 *
 * @author 张超
 * @date 2019-01-15
 */
public class Management extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 终端名称 */
	private String tname;
	/** 终端编号 */
	private String tid;
	/** 所属分组编号 */
	private String aid;
	/** 地址 */
	private String address;
	/** 管理员姓名 */
	private String uname;
	/** 所属区域 */
	private String aname;
	/** 联系电话 */
	private String phone;
	/** 基础设置 */
	private String basestation;
	/** 经度 */
	private String latitude;
	/** 纬度 */
	private String longitude;
	/** 更新时间 */
	private String receivetime;
	/** 创建时间 */
	private String createdtime;
	/** 终端管理员用户编号 */
	private String manageruserid;
	/** 终端管理员用户姓名 */
	private String managername;

	/** 终端是否可用 */
	private Boolean isuse;

	public void setTid(String tid)
	{
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTid() {
		return tid;
	}

	public void setAid(String aid)
	{
		this.aid = aid;
	}

	public String getAid()
	{
		return aid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBasestation() {
		return basestation;
	}

	public void setBasestation(String basestation) {
		this.basestation = basestation;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(String receivetime) {
		this.receivetime = receivetime;
	}

	public String getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}

	public String getManageruserid() {
		return manageruserid;
	}

	public void setManageruserid(String manageruserid) {
		this.manageruserid = manageruserid;
	}

	public String getManagername() {
		return managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}

	public Boolean getIsuse() { return isuse; }

	public void setIsuse(Boolean isuse) { this.isuse = isuse; }

	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("tname", getTname())
				.append("tid", getTid())
				.append("aid", getAid())
				.append("address", getAddress())
				.append("uname", getUname())
				.append("aname", getAname())
				.append("phone", getPhone())
				.append("basestation", getBasestation())
				.append("latitude", getLatitude())
				.append("longitude", getLongitude())
				.append("receivetime", getReceivetime())
				.append("createdtime", getCreatedtime())
				.append("manageruserid", getManageruserid())
				.append("managername", getManagername())
				.append("isuse", getIsuse())
				.toString();
	}
}
