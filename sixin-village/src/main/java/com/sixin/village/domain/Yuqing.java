package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 舆情上报表 yuqing
 *
 * @author 张鸿权
 * @date 2019-08-18
 */
public class Yuqing extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 舆情数据编号 */
	private Integer yqid;
	/** 舆情类别 */
	@Excel(name="舆情类别")
	private String yqtype;
	/** 舆情内容 */
	@Excel(name="舆情内容")
	private String content;
	/** 舆情图片地址 */
	@Excel(name="舆情图片地址")
	private String uploadimage;
	/** 舆情报告日期 */
	@Excel(name="舆情报告日期")
	private String adddate;
	/** 发送日期 */
	@Excel(name="发送日期")
	private String reldate;
	/** 报告人编号 */
	@Excel(name="报告人编号")
	private Integer adduid;
	/** 报告人姓名 */
	@Excel(name="报告人姓名")
	private String adduname;
	/** 发送人编号 */
	@Excel(name="发送人编号")
	private Integer uid;
	/** 发送人姓名 */
	@Excel(name="发送人姓名")
	private String uname;
	/** 用户所属地区村社区编号 */
	@Excel(name="用户所属地区村社区编号")
	private String aid;
	/** 是否有效 */
	@Excel(name="是否有效")
	private String remark;

	public void setYqid(Integer yqid)
	{
		this.yqid = yqid;
	}
	public Integer getYqid()
	{
		return yqid;
	}

	public void setYqtype(String yqtype)
	{
		this.yqtype = yqtype;
	}
	public String getYqtype()
	{
		return yqtype;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
	public String getContent()
	{
		return content;
	}

	public void setUploadimage(String uploadimage)
	{
		this.uploadimage = uploadimage;
	}
	public String getUploadimage()
	{
		return uploadimage;
	}

	public void setAdddate(String adddate)
	{
		this.adddate = adddate;
	}
	public String getAdddate()
	{
		return adddate;
	}

	public void setReldate(String reldate)
	{
		this.reldate = reldate;
	}
	public String getReldate()
	{
		return reldate;
	}

	public void setAdduid(Integer adduid)
	{
		this.adduid = adduid;
	}
	public Integer getAdduid()
	{
		return adduid;
	}

	public void setAdduname(String adduname)
	{
		this.adduname = adduname;
	}
	public String getAdduname()
	{
		return adduname;
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
				.append("yqid", getYqid())
				.append("yqtype", getYqtype())
				.append("content", getContent())
				.append("uploadimage", getUploadimage())
				.append("adddate", getAdddate())
				.append("reldate", getReldate())
				.append("adduid", getAdduid())
				.append("adduname", getAdduname())
				.append("uid", getUid())
				.append("uname", getUname())
				.append("aid", getAid())
				.append("remark", getRemark())
				.toString();
	}
}
