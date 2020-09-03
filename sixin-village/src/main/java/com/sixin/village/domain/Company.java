package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 企业表 company
 *
 * @author 张鸿权
 * @date 2019-08-08
 */
public class Company extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 数据编号 */
	private Integer cid;
	/** 地域编号 */
	@Excel(name="地域编号")
	private String aid;
	/** 企业名称 */
	@Excel(name="企业名称")
	private String cname;
	/** 社会信用代码 */
	@Excel(name="社会信用代码 ")
	private String scode;
	/** 负责人姓名 */
	@Excel(name="负责人姓名")
	private String pname;
	/** 电话 */
	@Excel(name="电话")
	private String phone;
	/** 企业地址 */
	@Excel(name="企业地址")
	private String address;
	/** 状态 */
	@Excel(name="状态")
	private String ctype;
	/** 建立日期 */
	@Excel(name="建立日期")
	private Date datetime;
	/** 删除标识 */
	@Excel(name="删除标识")
	private String dflag;
	/** 备注 */
	private String remark;

	public void setCid(Integer cid)
	{
		this.cid = cid;
	}

	public Integer getCid()
	{
		return cid;
	}
	public void setAid(String aid)
	{
		this.aid = aid;
	}

	public String getAid()
	{
		return aid;
	}
	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getCname()
	{
		return cname;
	}
	public void setScode(String scode)
	{
		this.scode = scode;
	}

	public String getScode()
	{
		return scode;
	}
	public void setPname(String pname)
	{
		this.pname = pname;
	}

	public String getPname()
	{
		return pname;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getPhone()
	{
		return phone;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getAddress()
	{
		return address;
	}
	public void setCtype(String ctype)
	{
		this.ctype = ctype;
	}

	public Date getDatetime() { return datetime; }
	public void setDatetime(Date datetime) { this.datetime = datetime; }

	public String getCtype()
	{
		return ctype;
	}
	public void setDflag(String dflag)
	{
		this.dflag = dflag;
	}

	public String getDflag()
	{
		return dflag;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getRemark()
	{
		return remark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("cid", getCid())
				.append("aid", getAid())
				.append("cname", getCname())
				.append("scode", getScode())
				.append("pname", getPname())
				.append("phone", getPhone())
				.append("address", getAddress())
				.append("ctype", getCtype())
				.append("datetime", getDatetime())
				.append("dflag", getDflag())
				.append("remark", getRemark())
				.toString();
	}
}
