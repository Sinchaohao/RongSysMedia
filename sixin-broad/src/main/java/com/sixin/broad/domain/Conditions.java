package com.sixin.broad.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sixin.common.base.BaseEntity;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 设备报表 ter_conditions
 * 
 * @author 张超
 * @date 2019-01-15
 */
public class Conditions extends BaseEntity
{
	private static final SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final long serialVersionUID = 1L;
	
	/** 终端号IMEI */
	private String tid;
	/** 硬件版本 */
	private String hversion;
	/** 软件版本 */
	private String sversion;
	/** 内核温度 */
	private String temp;
	/** 网络类型 */
	private String nettype;
	/** 信号质量 */
	private String qsignal;
	/** SD卡容量 */
	private String sd;
	/** MP3音量 */
	private String mp3;
	/** FM音量 */
	private String fm;
	/** GSM音量 */
	private String gsm;
	/** NULL音量 */
	private String nullv;
	/** 终端接入基站编号 */
	private String basestation;
	/** 创建时间 */
	private String createtime;
	/** 备注 */
	private String remark;
	/** 发射机的发射功率 */
	private Integer transmitpower;
	/** 反射功率 */
	private Integer reflectedpower;
	/** 工作电压 */
	private Integer workvoltage;
	/** FM调频发射RDS码 */
	private String rds;
	/** FM调频发射频率 */
	private String fmfrequency;
	/** 终端设备信息更新时间 */
	private String updatetime;
	/** 终端最后通信时间 */
	private String lastaccesstime;

	public Conditions(){

	}
	public Conditions(String tid){
		this.tid = tid;
	}
	public Conditions(Date date,String tid){
		this.createtime = df.format(date);
		this.tid = tid;
	}
	public void setTid(String tid) 
	{
		this.tid = tid;
	}

	public String getTid() 
	{
		return tid;
	}
	public void setHversion(String hversion) 
	{
		this.hversion = hversion;
	}

	public String getHversion() 
	{
		return hversion;
	}
	public void setSversion(String sversion) 
	{
		this.sversion = sversion;
	}

	public String getSversion() 
	{
		return sversion;
	}
	public void setTemp(String temp) 
	{
		this.temp = temp;
	}

	public String getTemp() 
	{
		return temp;
	}
	public void setNettype(String nettype) 
	{
		this.nettype = nettype;
	}

	public String getNettype() 
	{
		return nettype;
	}
	public void setQsignal(String qsignal) 
	{
		this.qsignal = qsignal;
	}

	public String getQsignal() 
	{
		return qsignal;
	}
	public void setSd(String sd) 
	{
		this.sd = sd;
	}

	public String getSd() 
	{
		return sd;
	}
	public void setMp3(String mp3) 
	{
		this.mp3 = mp3;
	}

	public String getMp3() 
	{
		return mp3;
	}
	public void setFm(String fm) 
	{
		this.fm = fm;
	}

	public String getFm() 
	{
		return fm;
	}
	public void setGsm(String gsm) 
	{
		this.gsm = gsm;
	}

	public String getGsm() 
	{
		return gsm;
	}
	public void setNullv(String nullv) 
	{
		this.nullv = nullv;
	}

	public String getNullv() 
	{
		return nullv;
	}
	public void setBasestation(String basestation) 
	{
		this.basestation = basestation;
	}

	public String getBasestation() 
	{
		return basestation;
	}
	public void setCreatetime(String createtime) 
	{
		this.createtime = createtime;
	}

	public String getCreatetime() 
	{
		return createtime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setTransmitpower(Integer transmitpower) 
	{
		this.transmitpower = transmitpower;
	}

	public Integer getTransmitpower() 
	{
		return transmitpower;
	}
	public void setReflectedpower(Integer reflectedpower) 
	{
		this.reflectedpower = reflectedpower;
	}

	public Integer getReflectedpower() 
	{
		return reflectedpower;
	}
	public void setWorkvoltage(Integer workvoltage) 
	{
		this.workvoltage = workvoltage;
	}

	public Integer getWorkvoltage() 
	{
		return workvoltage;
	}
	public void setRds(String rds) 
	{
		this.rds = rds;
	}

	public String getRds() 
	{
		return rds;
	}
	public void setFmfrequency(String fmfrequency) 
	{
		this.fmfrequency = fmfrequency;
	}

	public String getFmfrequency() 
	{
		return fmfrequency;
	}

	public String getupdatetime() {
		return updatetime;
	}

	public void setupdatetime(Date updatetime) {
		this.updatetime = df.format(updatetime);
	}

	public String getLastaccesstime() {
		return lastaccesstime;
	}

	public void setLastaccesstime(Date lastaccesstime) {
		this.lastaccesstime = df.format(lastaccesstime);
	}

	@Override
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tid", getTid())
            .append("hversion", getHversion())
            .append("sversion", getSversion())
            .append("temp", getTemp())
            .append("nettype", getNettype())
            .append("qsignal", getQsignal())
            .append("sd", getSd())
            .append("mp3", getMp3())
            .append("fm", getFm())
            .append("gsm", getGsm())
            .append("nullv", getNullv())
            .append("basestation", getBasestation())
            .append("createtime", getCreatetime())
            .append("remark", getRemark())
            .append("transmitpower", getTransmitpower())
            .append("reflectedpower", getReflectedpower())
            .append("workvoltage", getWorkvoltage())
            .append("rds", getRds())
            .append("fmfrequency", getFmfrequency())
            .toString();
    }
}
