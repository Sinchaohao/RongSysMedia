package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * led显示表 ledshow
 * 
 * @author 张超
 * @date 2019-01-14
 */
public class Ledshow extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** led显示编号 */
	private Integer lsid;
	/** 用户编号 */
	private String userid;
	/** led显示信息文字 */
	private String content;
	/** 终端接收信息后开始显示时间 */
	private String showtime;
	/** 创建时间 */
	private String createtime;
	/** 备注 */
	private String remark;

	public void setLsid(Integer lsid)
	{
		this.lsid = lsid;
	}

	public Integer getLsid() 
	{
		return lsid;
	}
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}

	public String getUserid() 
	{
		return userid;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setShowtime(String showtime) 
	{
		this.showtime = showtime;
	}

	public String getShowtime() 
	{
		return showtime;
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

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("lsid", getLsid())
            .append("userid", getUserid())
            .append("content", getContent())
            .append("showtime", getShowtime())
            .append("createtime", getCreatetime())
            .append("remark", getRemark())
            .toString();
    }
}
