package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 暮云党员表 myc_partymember
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
public class Partymember extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 数据编号 */
	private Integer pid;
	/** 地域编号 */
	@Excel(name = "地域编号")
	private String aid;
	/** 党员小组 */
	@Excel(name = "党员小组")
	private String grouptype;
	/** 党员姓名 */
	@Excel(name = "党员姓名")
	private String uname;
	/** 性别 */
	@Excel(name = "性别")
	private String sex;
	/** 民族 */
	@Excel(name = "名族")
	private String nations;
	/** 身份证号码 */
	@Excel(name = "身份证号码")
	private String idcard;
	/** 学历 */
	@Excel(name = "学历")
	private String edulevel;
	/** 出生年月 */
	@Excel(name = "出生年月")
	private String birthday;
	/** 入党时间 */
	@Excel(name = "入党时间")
	private String partyjoindate;
	/** 手机号 */
	@Excel(name = "手机号")
	private String phone;
	/** 家庭住址 */
	@Excel(name = "家庭住址")
	private String address;
	/** 备注 */
	@Excel(name = "备注")
	private String notes;
	/** 微信用户编号 */
	@Excel(name = "微信用户编号")
	private Integer uid;
	/** 微信号 */
	@Excel(name = "微信号")
	private String wcid;

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
	public void setUname(String uname) 
	{
		this.uname = uname;
	}

	public String getUname() 
	{
		return uname;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex() 
	{
		return sex;
	}
	public void setNations(String nations) 
	{
		this.nations = nations;
	}

	public String getNations() 
	{
		return nations;
	}
	public void setIdcard(String idcard) 
	{
		this.idcard = idcard;
	}

	public String getIdcard() 
	{
		return idcard;
	}
	public void setEdulevel(String edulevel) 
	{
		this.edulevel = edulevel;
	}

	public String getEdulevel() 
	{
		return edulevel;
	}
	public void setBirthday(String birthday) 
	{
		this.birthday = birthday;
	}

	public String getBirthday() 
	{
		return birthday;
	}
	public void setPartyjoindate(String partyjoindate) 
	{
		this.partyjoindate = partyjoindate;
	}

	public String getPartyjoindate() 
	{
		return partyjoindate;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setNotes(String notes) 
	{
		this.notes = notes;
	}

	public String getNotes() 
	{
		return notes;
	}
	public void setUid(Integer uid) 
	{
		this.uid = uid;
	}

	public Integer getUid() 
	{
		return uid;
	}
	public void setWcid(String wcid) 
	{
		this.wcid = wcid;
	}

	public String getWcid() 
	{
		return wcid;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pid", getPid())
            .append("aid", getAid())
            .append("grouptype", getGrouptype())
            .append("uname", getUname())
            .append("sex", getSex())
            .append("nations", getNations())
            .append("idcard", getIdcard())
            .append("edulevel", getEdulevel())
            .append("birthday", getBirthday())
            .append("partyjoindate", getPartyjoindate())
            .append("phone", getPhone())
            .append("address", getAddress())
            .append("notes", getNotes())
            .append("uid", getUid())
            .append("wcid", getWcid())
            .toString();
    }
}
