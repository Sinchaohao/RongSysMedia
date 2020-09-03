package com.sixin.village.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 村民基本表 myc_member
 * 
 * @author 张超
 * @date 2019-01-15
 */
public class Member extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 数据编号 */
	private Integer pid;
	/** 地域编号 */
	private String aid;
	/** 组别 */
	private String grouptype;
	/** 组内编号 */
	private Integer gid;
	/** 户主 */
	private String housename;
	/** 姓名 */
	private String name;
	/** 性别 */
	private String sex;
	/** 民族 */
	private String nation;
	/** 与户主关系 */
	private String relation;
	/** 身份证号码 */
	private String idcard;
	/** 学历 */
	private String edulevel;
	/** 出生年月 */
	private String birthday;
	/** 家庭住址 */
	private String address;
	/** 电话号码1 */
	private String phone1;
	/** 电话号码2 */
	private String phone2;
	/** 职业 */
	private String jod;
	/** 工作地点 */
	private String workplace;
	/** 年收入 */
	private String income;
	/** 银行卡号1 */
	private String bankcard1;
	/** 银行卡号2 */
	private String bankcard2;
	/** 银行卡号3 */
	private String bankcard3;
	/** 备注 */
	private String note;

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
	public void setGid(Integer gid) 
	{
		this.gid = gid;
	}

	public Integer getGid() 
	{
		return gid;
	}
	public void setHousename(String housename) 
	{
		this.housename = housename;
	}

	public String getHousename() 
	{
		return housename;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex() 
	{
		return sex;
	}
	public void setNation(String nation) 
	{
		this.nation = nation;
	}

	public String getNation() 
	{
		return nation;
	}
	public void setRelation(String relation) 
	{
		this.relation = relation;
	}

	public String getRelation() 
	{
		return relation;
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
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setPhone1(String phone1) 
	{
		this.phone1 = phone1;
	}

	public String getPhone1() 
	{
		return phone1;
	}
	public void setPhone2(String phone2) 
	{
		this.phone2 = phone2;
	}

	public String getPhone2() 
	{
		return phone2;
	}
	public void setJod(String jod) 
	{
		this.jod = jod;
	}

	public String getJod() 
	{
		return jod;
	}
	public void setWorkplace(String workplace) 
	{
		this.workplace = workplace;
	}

	public String getWorkplace() 
	{
		return workplace;
	}
	public void setIncome(String income) 
	{
		this.income = income;
	}

	public String getIncome() 
	{
		return income;
	}
	public void setBankcard1(String bankcard1) 
	{
		this.bankcard1 = bankcard1;
	}

	public String getBankcard1() 
	{
		return bankcard1;
	}
	public void setBankcard2(String bankcard2) 
	{
		this.bankcard2 = bankcard2;
	}

	public String getBankcard2() 
	{
		return bankcard2;
	}
	public void setBankcard3(String bankcard3) 
	{
		this.bankcard3 = bankcard3;
	}

	public String getBankcard3() 
	{
		return bankcard3;
	}
	public void setNote(String note) 
	{
		this.note = note;
	}

	public String getNote() 
	{
		return note;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pid", getPid())
            .append("aid", getAid())
            .append("grouptype", getGrouptype())
            .append("gid", getGid())
            .append("housename", getHousename())
            .append("name", getName())
            .append("sex", getSex())
            .append("nation", getNation())
            .append("relation", getRelation())
            .append("idcard", getIdcard())
            .append("edulevel", getEdulevel())
            .append("birthday", getBirthday())
            .append("address", getAddress())
            .append("phone1", getPhone1())
            .append("phone2", getPhone2())
            .append("jod", getJod())
            .append("workplace", getWorkplace())
            .append("income", getIncome())
            .append("bankcard1", getBankcard1())
            .append("bankcard2", getBankcard2())
            .append("bankcard3", getBankcard3())
            .append("note", getNote())
            .toString();
    }
}
