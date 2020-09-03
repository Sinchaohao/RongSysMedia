package com.sixin.broad.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;
import java.util.Date;

/**
 * 授权号码管理表 sys_phonemanage
 * @date 2020-02-21
 *
 */
public class Terminaladmin extends BaseEntity
{
    private static final long serialVersionUID=1L;

    /** 编号 */
    @Excel(name="编号")
    private BigInteger pid;

    /** 授权号码 */
    @Excel(name="授权号码")
    private String phone;
    /** 用户编号 */
    @Excel(name="用户编号")
    private BigInteger uid;
    /** 用户姓名 */
    @Excel(name="用户姓名")
    private String uname;

    /** 部门id */
    @Excel(name="部门id")
    private BigInteger deptid;
    /** 权限编码 */
    @Excel(name="权限编码")
    private String rolecode;
    /** 批准人编号 */
    @Excel(name="批准人编号")
    private BigInteger allowid;
    /** 批准人姓名 */
    @Excel(name="批准人姓名")
    private String allowname;
    /** 首次批准时间 */
    @Excel(name="首次批准时间")
    private Date allowdate;
    /** 再次批准人编号 */
    @Excel(name="再次批准人编号")
    private BigInteger reallowid;
    /** 再次批准人姓名 */
    @Excel(name="再次批准人姓名")
    private String reallowname;
    /** 批准人证明 */
    @Excel(name="批准人证明")
    private String allowpic;
    /** 备注 */
    @Excel(name="备注")
    private String demo;

    /** 0-无效,1-有效 */
    @Excel(name="0-无效,1-有效")
    private String remark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigInteger getPid() {
        return pid;
    }

    public void setPid(BigInteger pid) {
        this.pid = pid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigInteger getUid() {
        return uid;
    }

    public void setUid(BigInteger uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public BigInteger getAllowid() {
        return allowid;
    }

    public void setAllowid(BigInteger allowid) {
        this.allowid = allowid;
    }

    public String getAllowname() {
        return allowname;
    }

    public void setAllowname(String allowname) {
        this.allowname = allowname;
    }

    public Date getAllowdate() {
        return allowdate;
    }

    public void setAllowdate(Date allowdate) {
        this.allowdate = allowdate;
    }

    public BigInteger getReallowid() {
        return reallowid;
    }

    public void setReallowid(BigInteger reallowid) {
        this.reallowid = reallowid;
    }

    public String getReallowname() {
        return reallowname;
    }

    public void setReallowname(String reallowname) {
        this.reallowname = reallowname;
    }

    public String getAllowpic() {
        return allowpic;
    }

    public void setAllowpic(String allowpic) {
        this.allowpic = allowpic;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("pid",getPid())
                .append("phone",getPhone())
                .append("uid",getUid())
                .append("uname",getUname())
                .append("deptid",getDeptid())
                .append("rolecode",getRolecode())
                .append("allowid",getAllowid())
                .append("allowname",getAllowname())
                .append("allowdate",getAllowdate())
                .append("reallowid",getReallowid())
                .append("reallowname",getReallowname())
                .append("allowpic",getAllowpic())
                .append("demo",getDemo())
                .append("remark",getRemark())
                .toString();
    }

    public BigInteger getDeptid() {
        return deptid;
    }

    public void setDeptid(BigInteger deptid) {
        this.deptid = deptid;
    }
}
