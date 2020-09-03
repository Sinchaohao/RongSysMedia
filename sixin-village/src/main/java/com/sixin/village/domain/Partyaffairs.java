package com.sixin.village.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 党务公开表 tb_partyaffairs
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
public class Partyaffairs extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 党务编号 */
	private Integer dwid;
	/** 党务标题 */
	private String title;
	/** 党务内容 */
	private String content;
	/** 发布时间 */
	private String adddate;
	/** 图片 */
	private String pic;
	/** 发布人编号 */
	private Integer uid;
	/** 发布人姓名 */
	private String uname;
	/** 地区编码 */
	private String aid;
	/** 是否有效 */
	private String remark;

	public void setDwid(Integer dwid) 
	{
		this.dwid = dwid;
	}

	public Integer getDwid() 
	{
		return dwid;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setAdddate(String adddate) 
	{
		this.adddate = adddate;
	}

	public String getAdddate() 
	{
		return adddate;
	}
	public void setPic(String pic) 
	{
		this.pic = pic;
	}

	public String getPic() 
	{
		return pic;
	}
	public void setUid(Integer uid) 
	{
		this.uid = uid;
	}

	public Integer getUid() 
	{
		return uid;
	}
	public void setUname(String uname) 
	{
		this.uname = uname;
	}

	public String getUname() 
	{
		return uname;
	}
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
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
            .append("dwid", getDwid())
            .append("title", getTitle())
            .append("content", getContent())
            .append("adddate", getAdddate())
            .append("pic", getPic())
            .append("uid", getUid())
            .append("uname", getUname())
            .append("aid", getAid())
            .append("remark", getRemark())
            .toString();
    }
}
