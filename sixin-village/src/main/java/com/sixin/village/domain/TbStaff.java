package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 工作员表 tb_staff
 *
 * @author 张鸿权
 * @date 2019-11-03
 */
public class TbStaff extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 工作人员编号 */
	@Excel(name="工作人员编号")
	private String stid;
	/** 用户编号 */
	@Excel(name="用户编号")
	private String userid;
	/** 地域编号 */
	@Excel(name="地域编号")
	private String aid;
	/** 姓名 */
	@Excel(name="姓名")
	private String username;
	/** 年龄 */
	@Excel(name="年龄")
	private Integer age;
	/** 性别 */
	@Excel(name="性别")
	private String sex;
	/** 籍贯 */
	@Excel(name="籍贯 ")
	private String nativeplace;
	/** 家庭地址 */
	@Excel(name="家庭地址")
	private String address;
	/** 民族 */
	@Excel(name="民族 ")
	private String nation;
	/** 政治面貌 */
	@Excel(name="政治面貌 ")
	private String politics;
	/** 文化程度 */
	@Excel(name="文化程度")
	private String edulevel;
	/** 身份证号码 */
	@Excel(name="身份证号码")
	private String idcard;
	/** 联系方式 */
	@Excel(name="联系方式")
	private String phone;
	/** 职业 */
	@Excel(name="职业")
	private String job;
	/** 工作单位 */
	@Excel(name="工作单位")
	private String employeeunit;
	/** 获奖情况 */
	@Excel(name="获奖情况")
	private String winaward;
	/** 处分情况 */
	@Excel(name="处分情况")
	private String punish;
	/** 身份证照片 */
	private String idphoto;
	/** 是否有效 */
	private Integer isuse;

	public void setStid(String stid)
	{
		this.stid = stid;
	}

	public String getStid()
	{
		return stid;
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
	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUsername()
	{
		return username;
	}
	public void setAge(Integer age)
	{
		this.age = age;
	}

	public Integer getAge()
	{
		return age;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getSex()
	{
		return sex;
	}
	public void setNativeplace(String nativeplace)
	{
		this.nativeplace = nativeplace;
	}

	public String getNativeplace()
	{
		return nativeplace;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getAddress()
	{
		return address;
	}
	public void setNation(String nation)
	{
		this.nation = nation;
	}

	public String getNation()
	{
		return nation;
	}
	public void setPolitics(String politics)
	{
		this.politics = politics;
	}

	public String getPolitics()
	{
		return politics;
	}
	public void setEdulevel(String edulevel)
	{
		this.edulevel = edulevel;
	}

	public String getEdulevel()
	{
		return edulevel;
	}
	public void setIdcard(String idcard)
	{
		this.idcard = idcard;
	}

	public String getIdcard()
	{
		return idcard;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getPhone()
	{
		return phone;
	}
	public void setJob(String job)
	{
		this.job = job;
	}

	public String getJob()
	{
		return job;
	}
	public void setEmployeeunit(String employeeunit)
	{
		this.employeeunit = employeeunit;
	}

	public String getEmployeeunit()
	{
		return employeeunit;
	}
	public void setWinaward(String winaward)
	{
		this.winaward = winaward;
	}

	public String getWinaward()
	{
		return winaward;
	}
	public void setPunish(String punish)
	{
		this.punish = punish;
	}

	public String getPunish()
	{
		return punish;
	}
	public void setIdphoto(String idphoto)
	{
		this.idphoto = idphoto;
	}

	public String getIdphoto()
	{
		return idphoto;
	}
	public void setIsuse(Integer isuse)
	{
		this.isuse = isuse;
	}

	public Integer getIsuse()
	{
		return isuse;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("stid", getStid())
				.append("userid", getUserid())
				.append("aid", getAid())
				.append("username", getUsername())
				.append("age", getAge())
				.append("sex", getSex())
				.append("nativeplace", getNativeplace())
				.append("address", getAddress())
				.append("nation", getNation())
				.append("politics", getPolitics())
				.append("edulevel", getEdulevel())
				.append("idcard", getIdcard())
				.append("phone", getPhone())
				.append("job", getJob())
				.append("employeeunit", getEmployeeunit())
				.append("winaward", getWinaward())
				.append("punish", getPunish())
				.append("idphoto", getIdphoto())
				.append("isuse", getIsuse())
				.toString();
	}
}
