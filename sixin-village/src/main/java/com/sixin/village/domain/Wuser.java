package com.sixin.village.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 用户基本表 tb_wuser
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
public class Wuser extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private Integer uid;
	/** 微信名 */
	private String uname;
	/** 用户所属地区村社区 aid */
	private String aid;
	/** 用户所在小组 */
	private String wgroup;
	/** 用户类型编号 */
	private Integer rid;
	/** 用户类型 */
	private String utypes;
	/** 登录名 */
	private String loginid;
	/** 密码 */
	private String password;
	/** 用户头像 */
	private String headimg;
	/** 手机号码 */
	private String phone;
	/** 电子邮件 */
	private String uemail;
	/** 身份证号 */
	private String idcard;
	/** 住址 */
	private String address;
	/** 性别 */
	private String usex;
	/** 微信号 */
	private String wcid;
	/** 微信昵称 */
	private String wcname;
	/** 注册时间 */
	private String regdate;
	/** 最后登陆时间 */
	private String lastdate;
	/** 备注 */
	private String note;
	/** 是否可用 */
	private Integer isuse;
	/** 是党员为1 不是为0 */
	private Integer isparty;
	/** 是否有效 */
	private String remark;
	/** 管理员为1用户为0 */
	private Integer type;

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
	public void setWgroup(String wgroup) 
	{
		this.wgroup = wgroup;
	}

	public String getWgroup() 
	{
		return wgroup;
	}
	public void setRid(Integer rid) 
	{
		this.rid = rid;
	}

	public Integer getRid() 
	{
		return rid;
	}
	public void setUtypes(String utypes) 
	{
		this.utypes = utypes;
	}

	public String getUtypes() 
	{
		return utypes;
	}
	public void setLoginid(String loginid) 
	{
		this.loginid = loginid;
	}

	public String getLoginid() 
	{
		return loginid;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}
	public void setHeadimg(String headimg) 
	{
		this.headimg = headimg;
	}

	public String getHeadimg() 
	{
		return headimg;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setUemail(String uemail) 
	{
		this.uemail = uemail;
	}

	public String getUemail() 
	{
		return uemail;
	}
	public void setIdcard(String idcard) 
	{
		this.idcard = idcard;
	}

	public String getIdcard() 
	{
		return idcard;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setUsex(String usex) 
	{
		this.usex = usex;
	}

	public String getUsex() 
	{
		return usex;
	}
	public void setWcid(String wcid) 
	{
		this.wcid = wcid;
	}

	public String getWcid() 
	{
		return wcid;
	}
	public void setWcname(String wcname) 
	{
		this.wcname = wcname;
	}

	public String getWcname() 
	{
		return wcname;
	}
	public void setRegdate(String regdate) 
	{
		this.regdate = regdate;
	}

	public String getRegdate() 
	{
		return regdate;
	}
	public void setLastdate(String lastdate) 
	{
		this.lastdate = lastdate;
	}

	public String getLastdate() 
	{
		return lastdate;
	}
	public void setNote(String note) 
	{
		this.note = note;
	}

	public String getNote() 
	{
		return note;
	}
	public void setIsuse(Integer isuse) 
	{
		this.isuse = isuse;
	}

	public Integer getIsuse() 
	{
		return isuse;
	}
	public void setIsparty(Integer isparty) 
	{
		this.isparty = isparty;
	}

	public Integer getIsparty() 
	{
		return isparty;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getRemark()
	{
		return remark;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("uname", getUname())
            .append("aid", getAid())
            .append("wgroup", getWgroup())
            .append("rid", getRid())
            .append("utypes", getUtypes())
            .append("loginid", getLoginid())
            .append("password", getPassword())
            .append("headimg", getHeadimg())
            .append("phone", getPhone())
            .append("uemail", getUemail())
            .append("idcard", getIdcard())
            .append("address", getAddress())
            .append("usex", getUsex())
            .append("wcid", getWcid())
            .append("wcname", getWcname())
            .append("regdate", getRegdate())
            .append("lastdate", getLastdate())
            .append("note", getNote())
            .append("isuse", getIsuse())
            .append("isparty", getIsparty())
            .append("remark", getRemark())
            .append("type", getType())
            .toString();
    }
}
