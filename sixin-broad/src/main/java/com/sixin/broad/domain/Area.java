package com.sixin.broad.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 终端地域表 area
 * 
 * @author 张超
 * @date 2019-01-17
 */
public class Area extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 地域ID */
	@Excel(name = "地域编号")
	private long aid;

	/** 父地域ID */
	@Excel(name = "父级编号")
	private long parentaid;
	/** 地域名称 */
	@Excel(name = "地域名称")
	private String aname;
	/** 备注 */
	@Excel(name = "备注说明")
	private String note;

	public void setAid(long aid)
	{
		this.aid = aid;
	}

	public long getAid()
	{
		return aid;
	}
	public void setParentaid(long parentaid)
	{
		this.parentaid = parentaid;
	}

	public long getParentaid()
	{
		return parentaid;
	}
	public void setAname(String aname) 
	{
		this.aname = aname;
	}

	public String getAname() 
	{
		return aname;
	}
	public void setNote(String note) 
	{
		this.note = note;
	}

	public String getNote() 
	{
		return note;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("aid", getAid())
            .append("parentaid", getParentaid())
            .append("aname", getAname())
            .append("note", getNote())
            .toString();
    }
}
