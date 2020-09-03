package com.sixin.village.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;
import java.util.Date;

/**
 * 文件管理表 tb_filemanager
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
public class Filemanager extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 文件编号 */
	private Integer fileid;
	/** 文件名称 */
	private String filename;
	/** 上传时间 */
	private Date uploaddate;
	/** 用户编号 */
	private Integer uid;
	/** 上传人 */
	private String uname;
	/** 文件路径 */
	private String url;
	/** 文件大小 */
	private String filesize;
	/** 下载次数 */
	private Integer downloadnum;
	/** 点赞次数 */
	private Integer zan;
	/** 是否有效 */
	private String remark;
	/** 文件名 去掉后缀 */
	private String fsname;
	/** 地域编号 */
	private String aid;
	/** 0为在线学习文件；1：村务资料 2：日常资料 */
	private Integer type;

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
	public void setUploaddate(Date uploaddate) 
	{
		this.uploaddate = uploaddate;
	}

	public Date getUploaddate() 
	{
		return uploaddate;
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
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getUrl() 
	{
		return url;
	}
	public void setFilesize(String filesize) 
	{
		this.filesize = filesize;
	}

	public String getFilesize() 
	{
		return filesize;
	}
	public void setDownloadnum(Integer downloadnum) 
	{
		this.downloadnum = downloadnum;
	}

	public Integer getDownloadnum() 
	{
		return downloadnum;
	}
	public void setZan(Integer zan) 
	{
		this.zan = zan;
	}

	public Integer getZan() 
	{
		return zan;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getRemark()
	{
		return remark;
	}
	public void setFsname(String fsname) 
	{
		this.fsname = fsname;
	}

	public String getFsname() 
	{
		return fsname;
	}
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
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
            .append("fileid", getFileid())
            .append("filename", getFilename())
            .append("uploaddate", getUploaddate())
            .append("uid", getUid())
            .append("uname", getUname())
            .append("url", getUrl())
            .append("filesize", getFilesize())
            .append("downloadnum", getDownloadnum())
            .append("zan", getZan())
            .append("remark", getRemark())
            .append("fsname", getFsname())
            .append("aid", getAid())
            .append("type", getType())
            .toString();
    }
}
