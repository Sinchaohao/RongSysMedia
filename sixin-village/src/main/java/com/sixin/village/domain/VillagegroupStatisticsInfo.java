package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 村组统计表 villagegroup_statistics_info
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public class VillagegroupStatisticsInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 村组统计数据编号 */
	private Integer vgsid;
	/** 地域编号 */
	@Excel(name = "地域编号")
	private String aid;
	/** 村组名称 */
	@Excel(name = "组名管理")
	private String grouptype;
	/** 总面积 */
	@Excel(name = "总面积")
	private String area;
	/** 农用地小计 */
	@Excel(name = "农用地小计")
	private String farmland;
	/** 耕地 */
	@Excel(name = "耕地")
	private String plowland;
	/** 园地 */
	@Excel(name = "园地")
	private String orchard;
	/** 林地 */
	@Excel(name = "林地")
	private String forest;
	/** 农田水利设施用地 */
	@Excel(name = "农田水利用地")
	private String fland;
	/** 养殖水面 */
	@Excel(name = "养殖水产")
	private String aqarea;
	/** 其他农用地 */
	@Excel(name = "其他农用地")
	private String otherland;

	public void setVgsid(Integer vgsid) 
	{
		this.vgsid = vgsid;
	}

	public Integer getVgsid() 
	{
		return vgsid;
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
	public void setArea(String area) 
	{
		this.area = area;
	}

	public String getArea() 
	{
		return area;
	}
	public void setFarmland(String farmland) 
	{
		this.farmland = farmland;
	}

	public String getFarmland() 
	{
		return farmland;
	}
	public void setPlowland(String plowland) 
	{
		this.plowland = plowland;
	}

	public String getPlowland() 
	{
		return plowland;
	}
	public void setOrchard(String orchard) 
	{
		this.orchard = orchard;
	}

	public String getOrchard() 
	{
		return orchard;
	}
	public void setForest(String forest) 
	{
		this.forest = forest;
	}

	public String getForest() 
	{
		return forest;
	}
	public void setFland(String fland) 
	{
		this.fland = fland;
	}

	public String getFland() 
	{
		return fland;
	}
	public void setAqarea(String aqarea) 
	{
		this.aqarea = aqarea;
	}

	public String getAqarea() 
	{
		return aqarea;
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
            .append("vgsid", getVgsid())
            .append("aid", getAid())
            .append("grouptype", getGrouptype())
            .append("area", getArea())
            .append("farmland", getFarmland())
            .append("plowland", getPlowland())
            .append("orchard", getOrchard())
            .append("forest", getForest())
            .append("fland", getFland())
            .append("aqarea", getAqarea())
            .append("otherland", getOtherland())
            .toString();
    }
}
