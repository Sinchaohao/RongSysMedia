package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 耕地表 plowland_info
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public class PlowlandInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 耕地数据编号 */
	private Integer pid;
	/** 地域编号 */
	@Excel(name = "耕地编号")
	private String aid;
	/** 组名 */
	@Excel(name = "组名")
	private String grouptype;
	/** 集体经营 */
	@Excel(name = "集体经营")
	private String collective;
	/** 承包到户 */
	@Excel(name = "承包到户")
	private String contracted;

	public void setPid(Integer pid) 
	{
		this.pid = pid;
	}

	public Integer getPid() 
	{
		return pid;
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
            .append("pid", getPid())
            .append("aid", getAid())
            .append("grouptype", getGrouptype())
            .append("collective", getCollective())
            .append("contracted", getContracted())
            .toString();
    }
}
