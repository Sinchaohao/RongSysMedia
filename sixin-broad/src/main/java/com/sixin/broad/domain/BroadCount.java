package com.sixin.broad.domain;

import com.sixin.system.domain.SysDept;

/**
 * 公共节目单
 *
 * @author 周博
 * @date 2019-03-22
 */
public class BroadCount {
    private String uname;
    private int sfid;
    private int count;
    private String broaddate;
    private Long deptId;
    private SysDept dept;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getSfid() {
        return sfid;
    }

    public void setSfid(int sfid) {
        this.sfid = sfid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBroaddate() {
        return broaddate;
    }

    public void setBroaddate(String broaddate) {
        this.broaddate = broaddate;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }
}
