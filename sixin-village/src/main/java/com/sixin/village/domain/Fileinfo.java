package com.sixin.village.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 文件夹管理表 fileinfo
 * 
 * @author 张鸿权
 * @date 2019-08-22
 */
public class Fileinfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 文件编号 */
	private Integer fileid;
	/** 文件全称 */
	private String filename;
	/** 文件下载地址 */
	private String furl;
	/** 文件大小 */
	private String filesize;
	/** 上传时间 */
	private String pushdate;
	/** 文件名 去掉后缀 */
	private String fsname;
	/** 文件后缀名 */
	private String suffix;
	/** 下载次数 */
	private Integer downloadnum;
	/** 用户编号 */
	private String uid;
	/** 用户名称 */
	private String uname;
	/** 地域编号 */
	private String aid;
	/** 是否有效 */
	private String remark;

	public void setFileid(Integer fileid) 
	{
		this.fileid = fileid;
	}

	public Integer getFileid() 
	{
		return fileid;
	}
	public void setFilename(String filename) 
	{
		this.filename = filename;
	}

	public String getFilename() 
	{
		return filename;
	}
	public void setFurl(String furl) 
	{
		this.furl = furl;
	}

	public String getFurl() 
	{
		return furl;
	}
	public void setFilesize(String filesize) 
	{
		this.filesize = filesize;
	}

	public String getFilesize() 
	{
		return filesize;
	}
	public void setPushdate(String pushdate) 
	{
		this.pushdate = pushdate;
	}

	public String getPushdate() 
	{
		return pushdate;
	}
	public void setFsname(String fsname) 
	{
		this.fsname = fsname;
	}

	public String getFsname() 
	{
		return fsname;
	}
	public void setSuffix(String suffix) 
	{
		this.suffix = suffix;
	}

	public String getSuffix() 
	{
		return suffix;
	}
	public void setDownloadnum(Integer downloadnum) 
	{
		this.downloadnum = downloadnum;
	}

	public Integer getDownloadnum() 
	{
		return downloadnum;
	}
	public void setUid(String uid) 
	{
		this.uid = uid;
	}

	public String getUid() 
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileid", getFileid())
            .append("filename", getFilename())
            .append("furl", getFurl())
            .append("filesize", getFilesize())
            .append("pushdate", getPushdate())
            .append("fsname", getFsname())
            .append("suffix", getSuffix())
            .append("downloadnum", getDownloadnum())
            .append("uid", getUid())
            .append("uname", getUname())
            .append("aid", getAid())
            .append("remark", getRemark())
            .toString();
    }
}
