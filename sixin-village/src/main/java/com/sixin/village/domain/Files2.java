package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;

import java.util.Date;

/**
 * 文件上传表 sys_files
 * 
 * @author yc
 * @date 2018-12-20
 */
public class Files2 extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String url;
	/**  */
	private String status;
	/**  */
	private String fileName;
	/**  */
	private String remark;
	/** 类型（0代表图片 1代表视频） */
	private String type;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;
	/** 创建者 */
	private String createBy;
	private String createByName;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	private String updateByName;
	/** 更新时间 */
	private Date updateTime;

	/**后缀**/
	private String suffix;

	private String content;

	/**修改标识 0 新增 1 修改**/
	private int updateFlag;

	private Long channelId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getUrl() 
	{
		return url;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getUpdateByName() {
		return updateByName;
	}

	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}

	@Override
	public String toString() {
		return "Files{" +
				"id=" + id +
				", url='" + url + '\'' +
				", status='" + status + '\'' +
				", fileName='" + fileName + '\'' +
				", remark='" + remark + '\'' +
				", type='" + type + '\'' +
				", delFlag='" + delFlag + '\'' +
				", createBy='" + createBy + '\'' +
				", createByName='" + createByName + '\'' +
				", createTime=" + createTime +
				", updateBy='" + updateBy + '\'' +
				", updateByName='" + updateByName + '\'' +
				", updateTime=" + updateTime +
				'}';
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public int getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(int updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
}
