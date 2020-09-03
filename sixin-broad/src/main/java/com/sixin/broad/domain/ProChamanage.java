package com.sixin.broad.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 节目转播管理表 pro_chamanage
 * 
 * @author 张超
 * @date 2019-03-02
 */
public class ProChamanage extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	@Excel(name = "编号")
	/** 转播频道管理 */
	private Integer cid;
	@Excel(name = "节目")
	/** 节目 */
	private String cname;
	@Excel(name = "频道")
	/** 频道 */
	private String frequencies;
	/** 所属用户 */
	@Excel(name = "所属用户")
	private String userid;
	/** 节目备注 */
	@Excel(name = "节目备注")
	private String pronote;
	/** 备注 */
	@Excel(name = "备注")
	private String remark;

	public void setCid(Integer cid) 
	{
		this.cid = cid;
	}

	public Integer getCid() 
	{
		return cid;
	}
	public void setCname(String cname) 
	{
		this.cname = cname;
	}

	public String getCname() 
	{
		return cname;
	}
	public void setFrequencies(String frequencies) 
	{
		this.frequencies = frequencies;
	}

	public String getFrequencies() 
	{
		return frequencies;
	}
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}

	public String getUserid() 
	{
		return userid;
	}
	public void setPronote(String pronote) 
	{
		this.pronote = pronote;
	}

	public String getPronote() 
	{
		return pronote;
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
            .append("cid", getCid())
            .append("cname", getCname())
            .append("frequencies", getFrequencies())
            .append("userid", getUserid())
            .append("pronote", getPronote())
            .append("remark", getRemark())
            .toString();
    }
}
