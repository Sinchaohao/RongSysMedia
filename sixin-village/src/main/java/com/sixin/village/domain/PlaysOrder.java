package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 播放顺序表 play_order
 * 
 * @author yc
 * @date 2018-12-25
 */
public class PlaysOrder extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/**  */
	private String fileName;
	/** 0 图片 1 视频 2 文字 */
	private Integer types;

	private Long channelId;
	/**  */
	private String ur;
	/**  */
	private String content;
	/** 播放顺序 */
	private Integer orderBy;
	/**  */
	private String remark;

	private String suffix;

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
	}
	public void setUr(String ur)
	{
		this.ur = ur;
	}

	public Integer getTypes() {
		return types;
	}

	public void setTypes(Integer types) {
		this.types = types;
	}

	public String getUr()
	{
		return ur;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getRemark()
	{
		return remark;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileName", getFileName())
            .append("type", getTypes())
            .append("ur", getUr())
            .append("content", getContent())
            .append("order", getOrderBy())
            .append("remark", getRemark())
            .toString();
    }

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
}
