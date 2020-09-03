package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 上级公告表 ann_high
 * 
 * @author 张超
 * @date 2019-01-14
 */
public class High extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 上级公告编号 */
	private Integer mhid;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 接收人编号 */
	private String userid;
	/** 所属分组（区域） */
	private String aid;
	/** 发送时间 */
	private String sendtime;
	/** 是否已读 */
	private Boolean isread;
	/** 发送用户编号 */
	private String senduserid;

	public void setMhid(Integer mhid) 
	{
		this.mhid = mhid;
	}

	public Integer getMhid() 
	{
		return mhid;
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
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}

	public String getUserid() 
	{
		return userid;
	}
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
	}
	public void setSendtime(String sendtime) 
	{
		this.sendtime = sendtime;
	}

	public String getSendtime() 
	{
		return sendtime;
	}
	public void setIsread(Boolean isread) 
	{
		this.isread = isread;
	}

	public Boolean getIsread() 
	{
		return isread;
	}
	public void setSenduserid(String senduserid) 
	{
		this.senduserid = senduserid;
	}

	public String getSenduserid() 
	{
		return senduserid;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mhid", getMhid())
            .append("title", getTitle())
            .append("content", getContent())
            .append("userid", getUserid())
            .append("aid", getAid())
            .append("sendtime", getSendtime())
            .append("isread", getIsread())
            .append("senduserid", getSenduserid())
            .toString();
    }
}
