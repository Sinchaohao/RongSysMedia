package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;
import java.util.Date;

/**
 * 党员活动表 tb_huodong
 * 
 * @author zx
 * @date 2019-11-16
 */
public class TbHuodong extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 活动编号 */
	private Integer hdid;
	/** 活动昵称 */
	@Excel(name = "活动名称")
	private String hdtitle;
	/** 活动类别 */
	@Excel(name = "活动类别")
	private String hdtype;
	/** 活动地点 */
	@Excel(name = "活动地点")
	private String hdloc;
	/** 活动人数 */
	@Excel(name = "活动人数")
	private String hdnum;
	/** 活动开始时间 */
	@Excel(name = "活动开始时间")
	private String hdbtime;
	/** 活动结束日期 */
	@Excel(name = "活动结束时间")
	private String hdetime;
	/** 活动图片 */
	private String hdpic;
	/** 活动简介 */
	private String hdsum;
	/** 活动添加日期 */
	@Excel(name = "活动添加日期")
	private Date hdadddate;
	/** 添加人编号 */
	private String uid;
	/** 发布人姓名 */
	@Excel(name = "发布人")
	private String uname;
	/** 用户所属地区村社区 aid */
	private String aid;
	/** 是否有效 */
	private String remark;

	public void setHdid(Integer hdid) 
	{
		this.hdid = hdid;
	}

	public Integer getHdid() 
	{
		return hdid;
	}
	public void setHdtitle(String hdtitle) 
	{
		this.hdtitle = hdtitle;
	}

	public String getHdtitle() 
	{
		return hdtitle;
	}
	public void setHdtype(String hdtype) 
	{
		this.hdtype = hdtype;
	}

	public String getHdtype() 
	{
		return hdtype;
	}
	public void setHdloc(String hdloc) 
	{
		this.hdloc = hdloc;
	}

	public String getHdloc() 
	{
		return hdloc;
	}
	public void setHdnum(String hdnum) 
	{
		this.hdnum = hdnum;
	}

	public String getHdnum() 
	{
		return hdnum;
	}
	public void setHdbtime(String hdbtime) 
	{
		this.hdbtime = hdbtime;
	}

	public String getHdbtime() 
	{
		return hdbtime;
	}
	public void setHdetime(String hdetime) 
	{
		this.hdetime = hdetime;
	}

	public String getHdetime() 
	{
		return hdetime;
	}
	public void setHdpic(String hdpic) 
	{
		this.hdpic = hdpic;
	}

	public String getHdpic() 
	{
		return hdpic;
	}
	public void setHdsum(String hdsum) 
	{
		this.hdsum = hdsum;
	}

	public String getHdsum() 
	{
		return hdsum;
	}
	public void setHdadddate(Date hdadddate) 
	{
		this.hdadddate = hdadddate;
	}

	public Date getHdadddate() 
	{
		return hdadddate;
	}
	public void setUid(String uid) 
	{
		this.uid = uid;
	}

	public String getUid() 
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("hdid", getHdid())
            .append("hdtitle", getHdtitle())
            .append("hdtype", getHdtype())
            .append("hdloc", getHdloc())
            .append("hdnum", getHdnum())
            .append("hdbtime", getHdbtime())
            .append("hdetime", getHdetime())
            .append("hdpic", getHdpic())
            .append("hdsum", getHdsum())
            .append("hdadddate", getHdadddate())
            .append("uid", getUid())
            .append("uname", getUname())
            .append("aid", getAid())
            .append("remark", getRemark())
            .toString();
    }
}
