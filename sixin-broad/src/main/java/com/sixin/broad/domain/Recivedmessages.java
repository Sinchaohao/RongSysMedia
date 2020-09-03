package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 短信接收记录表 recivedmessages
 *
 * @author 张超
 * @date 2019-01-11
 */
public class Recivedmessages extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 接收id */
	private Integer rmid;
	/** 接收号码 */
	private String rmobile;
	/** 接受内容 */
	private String rcontent;
	/** 是否可用 */
	private Boolean isread;
	/** 接收时间 */
	private String recivedtime;
	/** 截止时间 */
	private String endtime;
	/** 是否有效 */
	private String remark;

	public void setRmid(Integer rmid)
	{
		this.rmid = rmid;
	}

	public Integer getRmid()
	{
		return rmid;
	}
	public void setRmobile(String rmobile)
	{
		this.rmobile = rmobile;
	}

	public String getRmobile()
	{
		return rmobile;
	}
	public void setRcontent(String rcontent)
	{
		this.rcontent = rcontent;
	}

	public String getRcontent()
	{
		return rcontent;
	}
	public void setIsread(Boolean isread)
	{
		this.isread = isread;
	}

	public Boolean getIsread()
	{
		return isread;
	}
	public void setRecivedtime(String recivedtime)
	{
		this.recivedtime = recivedtime;
	}

	public String getRecivedtime()
	{
		return recivedtime;
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

	public void setEndtime(String endtime)
	{
		this.endtime = endtime;
	}

	public String getEndtime()
	{
		return endtime;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("rmid", getRmid())
				.append("rmobile", getRmobile())
				.append("rcontent", getRcontent())
				.append("isread", getIsread())
				.append("recivedtime", getRecivedtime())
				.append("endtime", getEndtime())
				.append("remark", getRemark())
				.toString();
	}
}
