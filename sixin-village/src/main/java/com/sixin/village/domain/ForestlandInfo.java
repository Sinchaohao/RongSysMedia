package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 林地表 forestland_info
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public class ForestlandInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 林地数据编号 */
	private Integer fid;
	/** 地域编号 */
	@Excel(name = "地域编号")
	private String aid;
	/** 组名 */
	@Excel(name = "组名")
	private String grouptype;
	/** 集体经营面积 */
	@Excel(name = "集体经营面积")
	private String collective;
	/** 承包到户面积 */
	@Excel(name = "承包到户面积")
	private String contracted;

	public void setFid(Integer fid) 
	{
		this.fid = fid;
	}

	public Integer getFid() 
	{
		return fid;
	}
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
	}
	public void setGrouptype(String grouptype) 
	{
		this.grouptype = grouptype;
	}

	public String getGrouptype() 
	{
		return grouptype;
	}
	public void setCollective(String collective) 
	{
		this.collective = collective;
	}

	public String getCollective() 
	{
		return collective;
	}
	public void setContracted(String contracted) 
	{
		this.contracted = contracted;
	}

	public String getContracted() 
	{
		return contracted;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fid", getFid())
            .append("aid", getAid())
            .append("grouptype", getGrouptype())
            .append("collective", getCollective())
            .append("contracted", getContracted())
            .toString();
    }
}
