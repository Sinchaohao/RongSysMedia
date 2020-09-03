package com.sixin.iot.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;
import java.util.Date;
import com.sixin.system.domain.SysUser;
import java.util.List;
/**
 * 终端运转表 torrent
 *
 * @author 小蔡
 * @date 2019-03-03
 */
public class Torrent extends BaseEntity
{
	@Excel(name="序号")
	private Long id;

	@Excel(name="终端IMEI号")
	private String imei;

	@Excel(name="终端类型")
	private String ctype;

	@Excel(name="采集网络地址")
	private String conip;

	@Excel(name="数据状态,是否")
	private String status;

	@Excel(name="安装时间")
	private Date startdate;
	private String beginStartdate;
	private String endStartdate;

	@Excel(name="更换时间")
	private Date exdate;
	private String beginExdate;
	private String endExdate;

	@Excel(name="存放位置编号")
	private Long deptid;

	@Excel(name="录入人编号")
	private long uid;

	@Excel(name="备注")
	private String demo;

	@Excel(name="是否有效")
	private String delflag;

	@Excel(name="录入人")
	private String user_name;
	/**在线状态**/
	@Excel(name="在线状态")
	private String online;
	/**最后通信时间**/
	@Excel(name="最后通信时间")
	private Date enddate;

	List<SysUser> users;
	List<String> types;

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public String getBeginStartdate() {
		return beginStartdate;
	}

	public void setBeginStartdate(String beginStartdate) {
		this.beginStartdate = beginStartdate;
	}

	public String getEndStartdate() {
		return endStartdate;
	}

	public void setEndStartdate(String endStartdate) {
		this.endStartdate = endStartdate;
	}

	public String getBeginExdate() {
		return beginExdate;
	}

	public void setBeginExdate(String beginExdate) {
		this.beginExdate = beginExdate;
	}

	public String getEndExdate() {
		return endExdate;
	}

	public void setEndExdate(String endExdate) {
		this.endExdate = endExdate;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getConip() {
		return conip;
	}

	public void setConip(String conip) {
		this.conip = conip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getExdate() {
		return exdate;
	}

	public void setExdate(Date exdate) {
		this.exdate = exdate;
	}

	public Long getDeptid() {
		return deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public List<SysUser> getUsers() {
		return users;
	}

	public void setUsers(List<SysUser> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("imei", getImei())
				.append("ctype", getCtype())
				.append("conip", getConip())
				.append("status", getStatus())
				.append("startdate", getStartdate())
				.append("exdate", getExdate())
				.append("deptid", getDeptid())
				.append("uid", getUid())
				.append("demo", getDemo())
				.append("delflag", getDelflag())
				.append("user_name", getUser_name())
				.toString();
	}
}
