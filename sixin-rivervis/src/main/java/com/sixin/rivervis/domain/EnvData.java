package com.sixin.rivervis.domain;
/**
 * @function:环境监测表 env_data
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:张超
 * @Description:
 * @Date:Created in 2020-01-27 17:40
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EnvData
{
	private static final long serialVersionUID = 1L;
	
	/** 数据编号 */
	private Integer id;
	/** 雨量数据 */
	private String rain;
	/** 流量数据 */
	private String flow;
	/** 河流水位 */
	private String wlevel;
	/** 河流酸碱度 */
	private String ph;
	/** 空气质量数据 */
	private String pm;
	/** 温度数据 */
	private String temp;
	/** 湿度数据 */
	private String hum;
	/** 区域编号 */
	private String aid;
	/** 数据采集时间 */
	private String time;
	/** 是否有效 */
	private Integer remark;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setRain(String rain) 
	{
		this.rain = rain;
	}

	public String getRain() 
	{
		return rain;
	}
	public void setFlow(String flow) 
	{
		this.flow = flow;
	}

	public String getFlow() 
	{
		return flow;
	}
	public void setWlevel(String wlevel) 
	{
		this.wlevel = wlevel;
	}

	public String getWlevel() 
	{
		return wlevel;
	}
	public void setPh(String ph) 
	{
		this.ph = ph;
	}

	public String getPh() 
	{
		return ph;
	}
	public void setPm(String pm) 
	{
		this.pm = pm;
	}

	public String getPm() 
	{
		return pm;
	}
	public void setTemp(String temp) 
	{
		this.temp = temp;
	}

	public String getTemp() 
	{
		return temp;
	}
	public void setHum(String hum) 
	{
		this.hum = hum;
	}

	public String getHum() 
	{
		return hum;
	}
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
	}
	public void setTime(String time) 
	{
		this.time = time;
	}

	public String getTime() 
	{
		return time;
	}
	public void setRemark(Integer remark)
	{
		this.remark = remark;
	}

	public Integer getRemark()
	{
		return remark;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rain", getRain())
            .append("flow", getFlow())
            .append("wlevel", getWlevel())
            .append("ph", getPh())
            .append("pm", getPm())
            .append("temp", getTemp())
            .append("hum", getHum())
            .append("aid", getAid())
            .append("time", getTime())
            .append("remark", getRemark())
            .toString();
    }
}
