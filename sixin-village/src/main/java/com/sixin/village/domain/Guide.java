package com.sixin.village.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 常用办事指南表 tb_guide
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
public class Guide extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 指南数据编号 */
	private Integer guid;
	/** 指南名称 */
	private String guname;
	/** 指南图片路径 */
	private String gupic;
	/** 指南类别 */
	private String gutype;
	/** 指南内容 */
	private String content;
	/** 指南地址 */
	private String address;
	/** 负责人姓名 */
	private String fname;
	/** 负责人手机号 */
	private String fphone;
	/** 指南点击数 */
	private Integer greadtotal;
	/** 指南发布日期 */
	private String pushdate;
	/** 发布人编号 */
	private Integer uid;
	/** 发布人姓名 */
	private String uname;
	/** 用户所属地区村社区 aid */
	private String aid;
	/** 是否有效 */
	private String remark;

	public void setGuid(Integer guid) 
	{
		this.guid = guid;
	}

	public Integer getGuid() 
	{
		return guid;
	}
	public void setGuname(String guname) 
	{
		this.guname = guname;
	}

	public String getGuname() 
	{
		return guname;
	}
	public void setGupic(String gupic) 
	{
		this.gupic = gupic;
	}

	public String getGupic() 
	{
		return gupic;
	}
	public void setGutype(String gutype) 
	{
		this.gutype = gutype;
	}

	public String getGutype() 
	{
		return gutype;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setFname(String fname) 
	{
		this.fname = fname;
	}

	public String getFname() 
	{
		return fname;
	}
	public void setFphone(String fphone) 
	{
		this.fphone = fphone;
	}

	public String getFphone() 
	{
		return fphone;
	}
	public void setGreadtotal(Integer greadtotal) 
	{
		this.greadtotal = greadtotal;
	}

	public Integer getGreadtotal() 
	{
		return greadtotal;
	}
	public void setPushdate(String pushdate) 
	{
		this.pushdate = pushdate;
	}

	public String getPushdate() 
	{
		return pushdate;
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
            .append("guid", getGuid())
            .append("guname", getGuname())
            .append("gupic", getGupic())
            .append("gutype", getGutype())
            .append("content", getContent())
            .append("address", getAddress())
            .append("fname", getFname())
            .append("fphone", getFphone())
            .append("greadtotal", getGreadtotal())
            .append("pushdate", getPushdate())
            .append("uid", getUid())
            .append("uname", getUname())
            .append("aid", getAid())
            .append("remark", getRemark())
            .toString();
    }
}
