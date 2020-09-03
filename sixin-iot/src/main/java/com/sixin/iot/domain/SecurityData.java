package com.sixin.iot.domain;

/**
 * @Systemname:湖南灾害应急管理系统
 * @Author:刘波
 * @Description:安防表定义
 * @Date:Created in 2020-02-16 17：59
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:综合训练系统V1.0
 * @Rewriting:刘波
 */

import com.sixin.common.base.BaseEntity;
import com.sixin.system.domain.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

public class SecurityData extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private long id;         //编号
    private String stype;    //安防类型
    private Long deptId;   //区域编号
    private String deptName; //区域名称
    private String sno;     //设备编号
    private String sname;  //产品型号名称
    private String producer;//生产企业名称
    private Date prodtime;   //生产时间
    private  BigDecimal price; //采购价格
    private Date endtime;     //失效时间
    private Date installtime;  //安装时间
    private String installer;  //安装人
    private String demo;    //备注
    private char delflag;  //是否有效

    private Securitype securitype;//安防类型
    private SysDept sysDept;    //区域表

    private int scount;
    public SecurityData(){

    }

    public SecurityData(long id, String stype,Long deptId, String sno, String sname, String producer, Date prodtime, BigDecimal price, Date endtime, Date installtime, String installer, String demo, char delflag){
        this.id=id;
        this.stype=stype;
        this.deptId=deptId;
        this.sno=sno;
        this.sname=sname;
        this.producer=producer;
        this.prodtime=prodtime;
        this.price=price;
        this.endtime=endtime;
        this.installtime=installtime;
        this.installer=installer;
        this.demo=demo;
        this.delflag=delflag;

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    //    public long getDeptId() {
//        return deptId;
//    }
//
//    public void setDeptId(long deptId) {
//        this.deptId = deptId;
//    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Date getProdtime() {
        return prodtime;
    }

    public void setProdtime(Date prodtime) {
        this.prodtime = prodtime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getInstalltime() {
        return installtime;
    }

    public void setInstalltime(Date installtime) {
        this.installtime = installtime;
    }

    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installer) {
        this.installer = installer;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public char getDelflag() {
        return delflag;
    }

    public void setDelflag(char delflag) {
        this.delflag = delflag;
    }

    public Securitype getSecuritype() {
        return securitype;
    }

    public void setSecuritype(Securitype securitype) {
        this.securitype = securitype;
    }

    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    public int getScount() {
        return scount;
    }

    public void setScount(int scount) {
        this.scount = scount;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("stype", getStype())
                .append("deptId", getDeptId())
                .append("sno",getSno())
                .append("sname",getSname())
                .append("producer",getProducer())
                .append("prodtime",getProdtime())
                .append("price",getPrice())
                .append("endtime",getEndtime())
                .append("installtime",getInstalltime())
                .append("installer",getInstaller())
                .append("demo", getDemo())
                .append("delflag", getDelflag())
                .append("scount",getScount())
                .toString();
    }
}
