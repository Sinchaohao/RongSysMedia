package com.sixin.village.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 地域表 tb_varea
 * 
 * @author 张超
 * @date 2019-01-20
 */
public class Varea extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 地域编号 */
	private String aid;
	/** 父地域编号 */
	private String parentaid;
	/** 地域昵称 */
	private String aname;
	/** 备注 */
	private String note;

	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
	}
	public void setParentaid(String parentaid) 
	{
		this.parentaid = parentaid;
	}

	public String getParentaid() 
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("aid", getAid())
            .append("parentaid", getParentaid())
            .append("aname", getAname())
            .append("note", getNote())
            .toString();
    }
}
