package com.sixin.village.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 党员自评表 tb_partyscore
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
public class Partyscore extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 评分数据编号 */
	private Integer sfid;
	/** 被评价党员编号 */
	private Integer pid;
	/** 被评价党员姓名 */
	private String pname;
	/** 性别 */
	private String sex;
	/** 出生年月 */
	private String birthday;
	/** 入党时间 */
	private String partyjoindate;
	/** 优秀 */
	private Integer excellent;
	/** 合格 */
	private Integer fine;
	/** 基本合格 */
	private Integer qualified;
	/** 不合格 */
	private Integer unqualified;
	/** 评分备注 */
	private String note;
	/** 评分日期 */
	private String adddate;
	/** 党员评分期数 */
	private Integer no;
	/** 评价人编号 */
	private Integer uid;
	/** 评价人姓名 */
	private String uname;
	/** 地区编号 */
	private String aid;
	/** 是否有效 */
	private String remark;

	public void setSfid(Integer sfid) 
	{
		this.sfid = sfid;
	}

	public Integer getSfid() 
	{
		return sfid;
	}
	public void setPid(Integer pid) 
	{
		this.pid = pid;
	}

	public Integer getPid() 
	{
		return pid;
	}
	public void setPname(String pname) 
	{
		this.pname = pname;
	}

	public String getPname() 
	{
		return pname;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex() 
	{
		return sex;
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
	public void setExcellent(Integer excellent) 
	{
		this.excellent = excellent;
	}

	public Integer getExcellent() 
	{
		return excellent;
	}
	public void setFine(Integer fine) 
	{
		this.fine = fine;
	}

	public Integer getFine() 
	{
		return fine;
	}
	public void setQualified(Integer qualified) 
	{
		this.qualified = qualified;
	}

	public Integer getQualified() 
	{
		return qualified;
	}
	public void setUnqualified(Integer unqualified) 
	{
		this.unqualified = unqualified;
	}

	public Integer getUnqualified() 
	{
		return unqualified;
	}
	public void setNote(String note) 
	{
		this.note = note;
	}

	public String getNote() 
	{
		return note;
	}
	public void setAdddate(String adddate) 
	{
		this.adddate = adddate;
	}

	public String getAdddate() 
	{
		return adddate;
	}
	public void setNo(Integer no) 
	{
		this.no = no;
	}

	public Integer getNo() 
	{
		return no;
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
            .append("sfid", getSfid())
            .append("pid", getPid())
            .append("pname", getPname())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("partyjoindate", getPartyjoindate())
            .append("excellent", getExcellent())
            .append("fine", getFine())
            .append("qualified", getQualified())
            .append("unqualified", getUnqualified())
            .append("note", getNote())
            .append("adddate", getAdddate())
            .append("no", getNo())
            .append("uid", getUid())
            .append("uname", getUname())
            .append("aid", getAid())
            .append("remark", getRemark())
            .toString();
    }
}
