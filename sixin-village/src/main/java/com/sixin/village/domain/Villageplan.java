package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 美丽乡村表 villageplan
 * 
 * @author 张鸿权
 * @date 2019-08-20
 */
public class Villageplan extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 数据编号 */
	private Integer mid;
	/** 信息类别 */
	@Excel(name = "类别")
	private String vtype;
	/** 标题 */
	@Excel(name = "标题")
	private String mtitle;
	/** 图片地址 */
	private String mpic;
	/** 内容 */
	private String mcontent;
	/** 发布人编号 */
	private Integer uid;
	/** 发布人姓名 */
	@Excel(name = "发布人")
	private String uname;
	/** 建立日期 */
	@Excel(name = "建立日期")
	private String pushdate;
	/** 状态 */
	@Excel(name = "状态")
	private Integer mtype;
	/** 用户所属地区编号 */
	private String aid;
	/** 阅读点击数 */
	@Excel(name = "点击数")
	private Integer greadtotal;
	/** 备注 */
	@Excel(name = "备注")
	private String note;

	public void setMid(Integer mid) 
	{
		this.mid = mid;
	}

	public Integer getMid() 
	{
		return mid;
	}
	public void setVtype(String vtype) 
	{
		this.vtype = vtype;
	}

	public String getVtype() 
	{
		return vtype;
	}
	public void setMtitle(String mtitle) 
	{
		this.mtitle = mtitle;
	}

	public String getMtitle() 
	{
		return mtitle;
	}
	public void setMpic(String mpic) 
	{
		this.mpic = mpic;
	}

	public String getMpic() 
	{
		return mpic;
	}
	public void setMcontent(String mcontent) 
	{
		this.mcontent = mcontent;
	}

	public String getMcontent() 
	{
		return mcontent;
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
	public void setPushdate(String pushdate) 
	{
		this.pushdate = pushdate;
	}

	public String getPushdate() 
	{
		return pushdate;
	}
	public void setMtype(Integer mtype) 
	{
		this.mtype = mtype;
	}

	public Integer getMtype() 
	{
		return mtype;
	}
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
	}
	public void setNote(String note) 
	{
		this.note = note;
	}

	public String getNote() 
	{
		return note;
	}
	public void setGreadtotal(Integer greadtotal)
	{
		this.greadtotal = greadtotal;
	}

	public Integer getGreadtotal()
	{
		return greadtotal;
	}
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mid", getMid())
            .append("vtype", getVtype())
            .append("mtitle", getMtitle())
            .append("mpic", getMpic())
            .append("mcontent", getMcontent())
            .append("uid", getUid())
            .append("uname", getUname())
            .append("pushdate", getPushdate())
            .append("mtype", getMtype())
            .append("aid", getAid())
            .append("note", getNote())
			.append("greadtotal", getGreadtotal())
            .toString();
    }
}
