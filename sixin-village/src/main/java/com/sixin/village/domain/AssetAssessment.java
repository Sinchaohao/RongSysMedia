package com.sixin.village.domain;
/**
 * @function:资产清查表 asset_assessment
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:张鸿权
 * @Description:
 * @Date:Created in 2020-01-28 14:40
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AssetAssessment extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */

	private Integer aaid;
	/**  */
	@Excel(name = "地域编号")
	private String aid;
	/**  */
	@Excel(name = "村组名称")
	private String grouptype;
	/**  */
	@Excel(name = "建设用地")
	private String constructionland;
	/**  */
	@Excel(name = "工矿仓储用地")
	private String imsland;
	/**  */
	@Excel(name = "商服用地")
	private String businessland;
	/**  */
	@Excel(name = "农村宅基地")
	private String homestead;
	/**  */
	@Excel(name = "公共服务用地")
	private String publicland;
	/**  */
	@Excel(name = "交通水利用地")
	private String twland;
	/**  */
	@Excel(name = "其他建设用地")
	private String otherland;

	public void setAaid(Integer aaid) 
	{
		this.aaid = aaid;
	}

	public Integer getAaid() 
	{
		return aaid;
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
	public void setConstructionland(String constructionland) 
	{
		this.constructionland = constructionland;
	}

	public String getConstructionland() 
	{
		return constructionland;
	}
	public void setImsland(String imsland) 
	{
		this.imsland = imsland;
	}

	public String getImsland() 
	{
		return imsland;
	}
	public void setBusinessland(String businessland)
	{
		this.businessland = businessland;
	}

	public String getBusinessland()
	{
		return businessland;
	}
	public void setHomestead(String homestead) 
	{
		this.homestead = homestead;
	}

	public String getHomestead() 
	{
		return homestead;
	}
	public void setPublicland(String publicland) 
	{
		this.publicland = publicland;
	}

	public String getPublicland() 
	{
		return publicland;
	}
	public void setTwland(String twland) 
	{
		this.twland = twland;
	}

	public String getTwland() 
	{
		return twland;
	}
	public void setOtherland(String otherland) 
	{
		this.otherland = otherland;
	}

	public String getOtherland() 
	{
		return otherland;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("aaid", getAaid())
            .append("aid", getAid())
            .append("grouptype", getGrouptype())
            .append("constructionland", getConstructionland())
            .append("imsland", getImsland())
            .append(" businessland", getBusinessland())
            .append("homestead", getHomestead())
            .append("publicland", getPublicland())
            .append("twland", getTwland())
            .append("otherland", getOtherland())
            .toString();
    }
}
