package com.sixin.broad.domain;

/**
 * @author 周博
 * @Description: 根据时间和地区对广播数目的统计
 * @ClassName BroadTime
 * @date 2019/3/21
 */

public class Buser {
    private String userid;
    private String uname;
    private int count;
    private String lenth;
    private String isPublic;
    private String Ptp;
    private Long deptId;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLenth() {
        return lenth;
    }

    public void setLenth(String lenth) {
        this.lenth = lenth;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getPtp() {
        return Ptp;
    }

    public void setPtp(String ptp) {
        Ptp = ptp;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
