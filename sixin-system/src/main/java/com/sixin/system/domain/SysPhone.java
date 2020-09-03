package com.sixin.system.domain;

import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;
import java.sql.Date;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
public class SysPhone {
    private BigInteger pid;
    private String phone;
    private BigInteger uid;
    private BigInteger deptid;
    private String uname;
    private String rolecode;
    private BigInteger allowid;
    private String allowname;
    private Date allowdate;
    private BigInteger reallowid;
    private String reallowname;
    private String allowpic;
    private int remark;
    private SysDept dept;

    public SysDept getDept()
    {
        if (dept == null)
        {
            dept = new SysDept();
        }
        return dept;
    }

    public void setDept(SysDept dept)
    {
        this.dept = dept;
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

    public BigInteger getDeptid() {
        return deptid;
    }

    public void setDeptid(BigInteger deptid) {
        this.deptid = deptid;
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

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("pid", getPid())
                .append("phone", getPhone())
                .append("uid", getUid())
                .append("uname", getUname())
                .append("deptid", getDeptid())
                .append("rolecode", getRolecode())
                .append("allowid", getAllowid())
                .append("allowname", getAllowid())
                .append("reallowid", getReallowid())
                .append("reallowname", getReallowname())
                .append("allowpic",getAllowpic())
                .append("remark",getRemark() )
                .append("dept",getDept())
                .toString();
    }
}
