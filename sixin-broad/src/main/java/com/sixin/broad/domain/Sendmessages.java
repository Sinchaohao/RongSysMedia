package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;

import java.util.List;

/**
 * 短信发送记录表 sendmessages
 *
 * @author 张超
 * @date 2019-01-11
 */
public class Sendmessages extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 短信id */
	private Integer smid;
	/** 手机号 */
	private String smobile;
	/** 内容 */
	private String scontent;
	/** 接收短信的终端编号，或接收短信的用户编号，或紧急节目申请短信的接收人姓名 */
	private String tid;
	/** 短信发布者编号 */
	private String senderid;
	/** 发送时间 */
	private String sendtime;
	/** 特殊语种 */
	private String spelanguage;
	/** 发送次数 */
	private Integer sendtimes;
	/** 截止时间 */
	private String endtime;
	/** 是否发送 */
	private Boolean issend;
	/** 是否有效 */
	private String remark;

	private List<String> tids;

	private Integer userid; //用户id

	private String recivedtime; //信息接收时间

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getSmid() {
		return smid;
	}

	public void setSmid(Integer smid) {
		this.smid = smid;
	}

	public String getSmobile() {
		return smobile;
	}

	public void setSmobile(String smobile) {
		this.smobile = smobile;
	}

	public String getScontent() {
		return scontent;
	}

	public void setScontent(String scontent) {
		this.scontent = scontent;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public String getSpelanguage() {
		return spelanguage;
	}

	public void setSpelanguage(String spelanguage) {
		this.spelanguage = spelanguage;
	}

	public Integer getSendtimes() {
		return sendtimes;
	}

	public void setSendtimes(Integer sendtimes) {
		this.sendtimes = sendtimes;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Boolean getIssend() {
		return issend;
	}

	public void setIssend(Boolean issend) {
		this.issend = issend;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<String> getTids() {
		return tids;
	}

	public void setTids(List<String> tids) {
		this.tids = tids;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getRecivedtime() {
		return recivedtime;
	}

	public void setRecivedtime(String recivedtime) {
		this.recivedtime = recivedtime;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("smid", smid)
				.append("smobile", smobile)
				.append("scontent", scontent)
				.append("tid", tid)
				.append("senderid", senderid)
				.append("sendtime", sendtime)
				.append("spelanguage", spelanguage)
				.append("sendtimes", sendtimes)
				.append("endtime", endtime)
				.append("issend", issend)
				.append("remark", remark)
				.append("tids", tids)
				.append("userid", userid)
				.append("recivedtime", recivedtime)
				.toString();
	}
}


//package com.sixin.broad.domain;
//
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//import com.sixin.common.base.BaseEntity;
//
//import java.util.List;
//
///**
// * 短信发送记录表 sendmessages
// *
// * @author 张超
// * @date 2019-01-11
// */
//public class Sendmessages extends BaseEntity
//{
//	private static final long serialVersionUID = 1L;
//
//	/** 短信id */
//	private Integer smid;
//	/** 手机号 */
//	private String smobile;
//	/** 内容 */
//	private String scontent;
//	/** 是否发送 */
//	private Boolean issend;
//	/** 发送时间 */
//	private String sendtime;
//	/** 截止时间 */
//	private String endtime;
//	/** 是否有效 */
//	private String remark;
//	/** 接收短信的终端编号，或接收短信的用户编号，或紧急节目申请短信的接收人姓名 */
//	private String tid;
//	/** 特殊语种 */
//	private String spelanguage;
//	/** 发送次数 */
//	private Integer sendtimes;
//
//	private List<String> tids;
//	private Integer userid; //用户id
//	private String recivedtime; //信息接收时间
//
//	public List<String> getTids() {
//		return tids;
//	}
//
//	public void setTids(List<String> tids) {
//		this.tids = tids;
//	}
//
//	public Integer getUserid() {
//		return userid;
//	}
//
//	public void setUserid(Integer userid) {
//		this.userid = userid;
//	}
//
//	public String getRecivedtime() {
//		return recivedtime;
//	}
//
//	public void setRecivedtime(String recivedtime) {
//		this.recivedtime = recivedtime;
//	}
//
//	public void setSmid(Integer smid)
//	{
//		this.smid = smid;
//	}
//
//	public Integer getSmid()
//	{
//		return smid;
//	}
//	public void setSmobile(String smobile)
//	{
//		this.smobile = smobile;
//	}
//
//	public String getSmobile()
//	{
//		return smobile;
//	}
//	public void setScontent(String scontent)
//	{
//		this.scontent = scontent;
//	}
//
//	public String getScontent()
//	{
//		return scontent;
//	}
//	public void setIssend(Boolean issend)
//	{
//		this.issend = issend;
//	}
//
//	public Boolean getIssend()
//	{
//		return issend;
//	}
//	public void setSendtime(String sendtime)
//	{
//		this.sendtime = sendtime;
//	}
//
//	public String getSendtime()
//	{
//		return sendtime;
//	}
//	@Override
//	public void setRemark(String remark)
//	{
//		this.remark = remark;
//	}
//
//	@Override
//	public String getRemark()
//	{
//		return remark;
//	}
//
//	public void setEndtime(String endtime)
//	{
//		this.endtime = endtime;
//	}
//
//	public String getEndtime()
//	{
//		return endtime;
//	}
//
//	public void setTid(String tid)
//	{
//		this.tid = tid;
//	}
//
//	public String getTid()
//	{
//		return tid;
//	}
//	public void setSpelanguage(String spelanguage)
//	{
//		this.spelanguage = spelanguage;
//	}
//
//	public String getSpelanguage()
//	{
//		return spelanguage;
//	}
//	public void setSendtimes(Integer sendtimes)
//	{
//		this.sendtimes = sendtimes;
//	}
//
//	public Integer getSendtimes()
//	{
//		return sendtimes;
//	}
//
//	@Override
//	public String toString() {
//		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//				.append("smid", getSmid())
//				.append("smobile", getSmobile())
//				.append("scontent", getScontent())
//				.append("issend", getIssend())
//				.append("sendtime", getSendtime())
//				.append("remark", getRemark())
//				.append("tid", getTid())
//				.append("spelanguage", getSpelanguage())
//				.append("sendtimes", getSendtimes())
//				.toString();
//	}
//}
