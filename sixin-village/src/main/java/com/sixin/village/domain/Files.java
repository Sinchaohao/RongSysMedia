package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;

/**
 * @program: RongSys-lyb
 * @description: 节目上传
 * @author: Mr.Liu
 * @create: 2019-02-26 19:30
 **/
public class Files extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String fid;//节目申请-节目ID
	private String fname;//节目名称
	private String userid;//用户ID
	private String remark;//备注
	private Boolean ispublic;//是否公共
	private String filename;//要求文稿名
	private String address;//要求文稿路径
	private String urls;//要求文稿路径
	private String createdtime;//申请提交时间
	private String flenth;//节目时长
	private Double fsize;//文件大小
	private Boolean islisten;//是否审听
	private Boolean ptype;//节目类型，是否是录制节目
	private String uname;//上传用户名

	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getIspublic() {
		return ispublic;
	}
	public void setIspublic(Boolean ispublic) {
		this.ispublic = ispublic;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUrls() {
		return urls;
	}
	public void setUrls(String urls) {
		this.urls = urls;
	}
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public String getFlenth() {
		return flenth;
	}
	public void setFlenth(String flenth) {
		this.flenth = flenth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getPtype() {
		return ptype;
	}
	public void setPtype(Boolean ptype) {
		this.ptype = ptype;
	}
	public Boolean getIslisten() {
		return islisten;
	}
	public void setIslisten(Boolean islisten) {
		this.islisten = islisten;
	}
	public Double getFsize() {
		return fsize;
	}
	public void setFsize(Double fsize) {
		this.fsize = fsize;
	}

	@Override
	public String toString() {
		return "Program{" +
				"fid='" + fid + '\'' +
				", fname='" + fname + '\'' +
				", userid='" + userid + '\'' +
				", remark='" + remark + '\'' +
				", ispublic=" + ispublic +
				", filename='" + filename + '\'' +
				", address='" + address + '\'' +
				", urls='" + urls + '\'' +
				", createdtime='" + createdtime + '\'' +
				", flenth='" + flenth + '\'' +
				", fsize=" + fsize +
				", islisten=" + islisten +
				", ptype=" + ptype +
				", uname='" + uname + '\'' +
				'}';
	}
}
