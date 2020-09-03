package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 村户表 house_member_info
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public class HouseMemberInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 数据编号 */
	private Integer hmid;
	/** 地域编号 */
	@Excel(name = "地域编号")
	private String aid;
	/** 家庭成员姓名 */
	@Excel(name = "家庭成员姓名")
	private String vname;
	/** 户主姓名 */
	@Excel(name = "户主姓名")
	private String housename;
	/** 与户主关系 */
	@Excel(name = "与户主关系")
	private String relation;
	/** 性别 */
	@Excel(name = "性别")
	private String sex;
	/** 身份证号码 */
	@Excel(name = "身份证号码")
	private String idcard;

	public void setHmid(Integer hmid) 
	{
		this.hmid = hmid;
	}

	public Integer getHmid() 
	{
		return hmid;
	}
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
	}
	public void setVname(String vname)
	{
		this.vname = vname;
	}

	public String getVname()
	{
		return vname;
	}
	public void setHousename(String housename) 
	{
		this.housename = housename;
	}

	public String getHousename() 
	{
		return housename;
	}
	public void setRelation(String relation) 
	{
		this.relation = relation;
	}

	public String getRelation() 
	{
		return relation;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex() 
	{
		return sex;
	}
	public void setIdcard(String idcard) 
	{
		this.idcard = idcard;
	}

	public String getIdcard() 
	{
		return idcard;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("hmid", getHmid())
            .append("aid", getAid())
            .append("vname", getVname())
            .append("housename", getHousename())
            .append("relation", getRelation())
            .append("sex", getSex())
            .append("idcard", getIdcard())
            .toString();
    }
}
