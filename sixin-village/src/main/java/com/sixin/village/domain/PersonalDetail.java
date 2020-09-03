package com.sixin.village.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 个人详细表 personal_detail
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public class PersonalDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer pdid;
	/** 地域编号 */
	private String aid;
	/** 村民姓名 */
	private String vname;
	/** 个人照片编号 */
	private String personpic;
	/** 身份证号码 */
	private String idcard;
	/** 身份证照片地址 */
	private String idcardpic;
	/** 车牌号 */
	private String carnum;
	/** 车辆照片地址 */
	private String carpic;

	public void setPdid(Integer pdid) 
	{
		this.pdid = pdid;
	}

	public Integer getPdid() 
	{
		return pdid;
	}
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
	}
	public void setName(String vname)
	{
		this.vname = vname;
	}

	public String getVname()
	{
		return vname;
	}
	public void setPersonpic(String personpic) 
	{
		this.personpic = personpic;
	}

	public String getPersonpic() 
	{
		return personpic;
	}
	public void setIdcard(String idcard) 
	{
		this.idcard = idcard;
	}

	public String getIdcard() 
	{
		return idcard;
	}
	public void setIdcardpic(String idcardpic) 
	{
		this.idcardpic = idcardpic;
	}

	public String getIdcardpic() 
	{
		return idcardpic;
	}
	public void setCarnum(String carnum) 
	{
		this.carnum = carnum;
	}

	public String getCarnum() 
	{
		return carnum;
	}
	public void setCarpic(String carpic) 
	{
		this.carpic = carpic;
	}

	public String getCarpic() 
	{
		return carpic;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pdid", getPdid())
            .append("aid", getAid())
            .append("vname", getVname())
            .append("personpic", getPersonpic())
            .append("idcard", getIdcard())
            .append("idcardpic", getIdcardpic())
            .append("carnum", getCarnum())
            .append("carpic", getCarpic())
            .toString();
    }
}
