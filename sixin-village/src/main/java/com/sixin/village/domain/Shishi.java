package com.sixin.village.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 实时政事表 tb_shishi
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
public class Shishi extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 实时政事数据编号 */
	private Integer shiid;
	/** 文章标题 */
	private String title;
	/** 类型 */
	private String type;
	/** 添加日期 */
	private String adddate;
	/** 修改日期 */
	private String reldate;
	/** 文章内容 */
	private String content;
	/** 文章照片路径 */
	private String pic;
	/** 发送人id编号 */
	private Integer uid;
	/** 发送人姓名 */
	private String uname;
	/** 地区编号 */
	private String aid;
	/** 是否有效 */
	private String remark;
	/** 视频地址 */
	private String video;

	public void setShiid(Integer shiid) 
	{
		this.shiid = shiid;
	}

	public Integer getShiid() 
	{
		return shiid;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setAdddate(String adddate) 
	{
		this.adddate = adddate;
	}

	public String getAdddate() 
	{
		return adddate;
	}
	public void setReldate(String reldate) 
	{
		this.reldate = reldate;
	}

	public String getReldate() 
	{
		return reldate;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setPic(String pic) 
	{
		this.pic = pic;
	}

	public String getPic() 
	{
		return pic;
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
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setVideo(String video) 
	{
		this.video = video;
	}

	public String getVideo() 
	{
		return video;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("shiid", getShiid())
            .append("title", getTitle())
            .append("type", getType())
            .append("adddate", getAdddate())
            .append("reldate", getReldate())
            .append("content", getContent())
            .append("pic", getPic())
            .append("uid", getUid())
            .append("uname", getUname())
            .append("aid", getAid())
            .append("remark", getRemark())
            .append("video", getVideo())
            .toString();
    }
}
