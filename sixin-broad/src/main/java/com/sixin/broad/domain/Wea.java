package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 天气公告表 ann_wea
 * 
 * @author 张超
 * @date 2019-01-14
 */
public class Wea extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 气象公告编号 */
	private Integer wid;
	/** 分发平台ID */
	private String tid;
	/** 预警编号 */
	private String wnum;
	/** 接收用户 */
	private String userid;
	/** 发布单位 */
	private String company;
	/** 渠道发布平台ID */
	private String qid;
	/** 对接手段 */
	private String means;
	/** 预警状态 */
	private String wstate;
	/** 信息 */
	private String mes;
	/** 创建时间 */
	private String createtime;
	/** 是否已读 */
	private Boolean isread;

	public void setWid(Integer wid) 
	{
		this.wid = wid;
	}

	public Integer getWid() 
	{
		return wid;
	}
	public void setTid(String tid) 
	{
		this.tid = tid;
	}

	public String getTid() 
	{
		return tid;
	}
	public void setWnum(String wnum) 
	{
		this.wnum = wnum;
	}

	public String getWnum() 
	{
		return wnum;
	}
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}

	public String getUserid() 
	{
		return userid;
	}
	public void setCompany(String company) 
	{
		this.company = company;
	}

	public String getCompany() 
	{
		return company;
	}
	public void setQid(String qid) 
	{
		this.qid = qid;
	}

	public String getQid() 
	{
		return qid;
	}
	public void setMeans(String means) 
	{
		this.means = means;
	}

	public String getMeans() 
	{
		return means;
	}
	public void setWstate(String wstate) 
	{
		this.wstate = wstate;
	}

	public String getWstate() 
	{
		return wstate;
	}
	public void setMes(String mes) 
	{
		this.mes = mes;
	}

	public String getMes() 
	{
		return mes;
	}
	public void setCreatetime(String createtime) 
	{
		this.createtime = createtime;
	}

	public String getCreatetime() 
	{
		return createtime;
	}
	public void setIsread(Boolean isread) 
	{
		this.isread = isread;
	}

	public Boolean getIsread() 
	{
		return isread;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wid", getWid())
            .append("tid", getTid())
            .append("wnum", getWnum())
            .append("userid", getUserid())
            .append("company", getCompany())
            .append("qid", getQid())
            .append("means", getMeans())
            .append("wstate", getWstate())
            .append("mes", getMes())
            .append("createtime", getCreatetime())
            .append("isread", getIsread())
            .toString();
    }
}
