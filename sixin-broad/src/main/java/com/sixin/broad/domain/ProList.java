package com.sixin.broad.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

/**
 * 节目单记录表 pro_list
 * 
 * @author 张超
 * @date 2019-03-02
 */
public class ProList extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	private String id;
	
	/** 节目编号 */
	@Excel(name = "播出单id")
	private String pid;
	/** 操作记录 */
	@Excel(name = "操作记录")
	private String ptp;
	/** 节目文件 */
	@Excel(name = "节目文件")
	private String fN;
	/** 文件编号 */
	@Excel(name = "文件编号")
	private String fid;
	/** 播放时间 */
	@Excel(name = "播放时间")
	private String bt;

	/** 播放时长，只有电台转播有 */
	@Excel(name = "播放时长")
	private String broadtime;

	/** 备注 */
	@Excel(name = "备注")
	private String remark;

	/** 节目文件路径 */
	@Excel(name = "节目文件路径")
	private String urls;
	/** 用户ID */
	private String userid;
	/** 播放日期 */
	private String broaddate;
	/** 终端编号 */
	private String terminalID;


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBroaddate() {
		return broaddate;
	}

	public void setBroaddate(String broaddate) {
		this.broaddate = broaddate;
	}

	public String getTerminalID() {
		return terminalID;
	}

	public void setTerminalID(String terminalID) {
		this.terminalID = terminalID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPid(String pid)
	{
		this.pid = pid;
	}

	public String getPid()
	{
		return pid;
	}
	public void setPtp(String ptp) 
	{
		this.ptp = ptp;
	}

	public String getPtp() 
	{
		return ptp;
	}
	public void setFid(String fid)
	{
		this.fid = fid;
	}

	public String getFid()
	{
		return fid;
	}
	public void setBt(String bt) 
	{
		this.bt = bt;
	}

	public String getBt() 
	{
		return bt;
	}
	public void setBroadtime(String broadtime) 
	{
		this.broadtime = broadtime;
	}

	public String getBroadtime() 
	{
		return broadtime;
	}
	@Override
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	@Override
	public String getRemark() 
	{
		return remark;
	}

	public String getfN() {
		return fN;
	}

	public void setfN(String fN) {
		this.fN = fN;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	@Override
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pid", getPid())
            .append("ptp", getPtp())
            .append("fN", getfN())
            .append("fid", getFid())
            .append("bt", getBt())
            .append("broadtime", getBroadtime())
            .append("remark", getRemark())
            .toString();
    }

}
