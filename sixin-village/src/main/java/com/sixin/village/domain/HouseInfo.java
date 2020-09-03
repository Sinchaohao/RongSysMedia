package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 房屋表 house_info
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public class HouseInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 房屋信息数据编号 */
	private Integer hid;
	/** 地域编号 */
	@Excel(name = "地域编号")
	private String aid;
	/** 户主姓名 */
	@Excel(name = "户主姓名")
	private String vname;
	/** 房屋照片地址 */
	@Excel(name = "房屋照片地址")
	private String housepic;
	/** 房屋面积 */
	@Excel(name = "房屋面积")
	private String area;
	/** 建房证照片地址 */
	@Excel(name = "建房照片地址")
	private String certificate;
	/** 房屋地址 */
	@Excel(name = "房屋地址")
	private String address;
	/** 信用社银行卡号 */
	@Excel(name = "信用社银行卡号")
	private String bankcard;
	/** 电力供电接入 */
	@Excel(name = "电力接入")
	private String psa;
	/** 有线电视接入 */
	@Excel(name = "有线电视接入")
	private String ca;
	/** 宽带网络接入 */
	@Excel(name = "宽带网络接入")
	private String woban;
	/** 自来水供应接入 */
	@Excel(name = "自来水接入")
	private String water;
	/** 安防系统接入 */
	@Excel(name = "安防系统接入")
	private String ssa;
	/** 垃圾分类及处理 */
	@Excel(name = "垃圾分类处理")
	private String wdr;
	/** 煤气燃料供应 */
	@Excel(name = "煤气燃料供应")
	private String gfs;
	/** 征收情况 */
	@Excel(name = "征收情况")
	private String levy;

	public void setHid(Integer hid) 
	{
		this.hid = hid;
	}

	public Integer getHid() 
	{
		return hid;
	}
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
	}
	public void setVname(String vname)
	{
		this.vname = vname;
	}

	public String getVname()
	{
		return vname;
	}
	public void setHousepic(String housepic) 
	{
		this.housepic = housepic;
	}

	public String getHousepic() 
	{
		return housepic;
	}
	public void setArea(String area) 
	{
		this.area = area;
	}

	public String getArea() 
	{
		return area;
	}
	public void setCertificate(String certificate) 
	{
		this.certificate = certificate;
	}

	public String getCertificate() 
	{
		return certificate;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setBankcard(String bankcard) 
	{
		this.bankcard = bankcard;
	}

	public String getBankcard() 
	{
		return bankcard;
	}
	public void setPsa(String psa) 
	{
		this.psa = psa;
	}

	public String getPsa() 
	{
		return psa;
	}
	public void setCa(String ca) 
	{
		this.ca = ca;
	}

	public String getCa() 
	{
		return ca;
	}
	public void setWoban(String woban) 
	{
		this.woban = woban;
	}

	public String getWoban() 
	{
		return woban;
	}
	public void setWater(String water) 
	{
		this.water = water;
	}

	public String getWater() 
	{
		return water;
	}
	public void setSsa(String ssa) 
	{
		this.ssa = ssa;
	}

	public String getSsa() 
	{
		return ssa;
	}
	public void setWdr(String wdr) 
	{
		this.wdr = wdr;
	}

	public String getWdr() 
	{
		return wdr;
	}
	public void setGfs(String gfs) 
	{
		this.gfs = gfs;
	}

	public String getGfs() 
	{
		return gfs;
	}
	public void setLevy(String levy) 
	{
		this.levy = levy;
	}

	public String getLevy() 
	{
		return levy;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("hid", getHid())
            .append("aid", getAid())
            .append("vname", getVname())
            .append("housepic", getHousepic())
            .append("area", getArea())
            .append("certificate", getCertificate())
            .append("address", getAddress())
            .append("bankcard", getBankcard())
            .append("psa", getPsa())
            .append("ca", getCa())
            .append("woban", getWoban())
            .append("water", getWater())
            .append("ssa", getSsa())
            .append("wdr", getWdr())
            .append("gfs", getGfs())
            .append("levy", getLevy())
            .toString();
    }
}
