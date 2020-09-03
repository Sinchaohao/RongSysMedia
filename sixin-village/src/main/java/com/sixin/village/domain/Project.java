package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 重大项目表 tb_project
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
public class Project extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 项目数据编号 */
	private Integer proid;
	/**  */
	@Excel(name = "项目名称")
	private String proname;
	/** 项目图片路径 */
	private String propic;
	/** 项目工作人员 */
	private String wpeopleid;
	/** 项目工作人员 */
	private String wpeoplename;
	/**  */
	@Excel(name = "项目类别")
	private String protype;
	/** 投资金额 */
	private Integer money;
	/** 开支明细 */
	private String moneynote;
	/** 项目内容 */
	private String content;
	/** 项目地址 */
	private String address;
	/** 负责人姓名 */
	private String fname;
	/** 负责人手机号 */
	private String fphone;
	/**  */
	@Excel(name = "项目点击数")
	private Integer greadtotal;
	/**  */
	@Excel(name = "项目发布日期")
	private String pushdate;
	/** 发布人编号 */
	private Integer uid;
	/**  */
	@Excel(name = "发布人")
	private String uname;
	/**  */
	@Excel(name = "是否有效")
	private String remark;
	/** 用户所属地区村社区 aid */
	private String aid;
	/**  */
	@Excel(name = "项目状态“0”为未建，“1”为在建，“2”为已建")
	private Integer ptype;

	public void setProid(Integer proid) 
	{
		this.proid = proid;
	}

	public Integer getProid() 
	{
		return proid;
	}
	public void setProname(String proname) 
	{
		this.proname = proname;
	}

	public String getProname() 
	{
		return proname;
	}
	public void setPropic(String propic) 
	{
		this.propic = propic;
	}

	public String getPropic() 
	{
		return propic;
	}
	public void setProtype(String protype) 
	{
		this.protype = protype;
	}

	public String getProtype() 
	{
		return protype;
	}
	public void setMoney(Integer money) 
	{
		this.money = money;
	}

	public Integer getMoney() 
	{
		return money;
	}
	public void setMoneynote(String moneynote) 
	{
		this.moneynote = moneynote;
	}

	public String getMoneynote() 
	{
		return moneynote;
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
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
	}
	public void setPtype(Integer ptype)
	{
		this.ptype = ptype;
	}

	public Integer getPtype()
	{
		return ptype;
	}
	public void setWpeopleid(String wpeopleid)
	{
		this.wpeopleid = wpeopleid;
	}

	public String getWpeopleid()
	{
		return wpeopleid;
	}
	public void setWpeoplename(String wpeoplename)
	{
		this.wpeoplename = wpeoplename;
	}

	public String getWpeoplename()
	{
		return wpeoplename;
	}
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("proid", getProid())
            .append("proname", getProname())
			.append("wpeopleid", getWpeopleid())
			.append("wpeoplename", getWpeoplename())
            .append("propic", getPropic())
            .append("protype", getProtype())
            .append("money", getMoney())
            .append("moneynote", getMoneynote())
            .append("content", getContent())
            .append("address", getAddress())
            .append("fname", getFname())
            .append("fphone", getFphone())
            .append("greadtotal", getGreadtotal())
            .append("pushdate", getPushdate())
            .append("uid", getUid())
            .append("uname", getUname())
            .append("remark", getRemark())
            .append("aid", getAid())
            .append("ptype", getPtype())
            .toString();
    }
}
