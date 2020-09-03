package com.sixin.broad.domain;

import java.util.List;

/**
 * @author 张超 teavamc
 * @Description: 节目播出单管理
 * @ClassName Progbroad
 * @date 2019/3/2 12:12
 **/
public class Progbroad {
    private String sfid;//编号
    private String scategory;//节目播出单类别
    private String createtime;//建立日期
    private String broaddate;//播出日期
    private String broadtimes;//紧急播出单播出次数
    private String userid;//操作（所属）用户编号
    private String uname;//操作（所属）用户
    private String remark;//备注
    private List<Program_List> prolist;//节目列表
//	private List<Area> arealist;//播出该节目单的分组
//	private List<Terminal> terminallist;//播出该节目单的终端


    public String getSfid() {
        return sfid;
    }
    public void setSfid(String sfid) {
        this.sfid = sfid;
    }
    public String getScategory() {
        return scategory;
    }
    public void setScategory(String scategory) {
        this.scategory = scategory;
    }
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getBroaddate() {
        return broaddate;
    }
    public void setBroaddate(String broaddate) {
        this.broaddate = broaddate;
    }
    public List<Program_List> getProlist() {
        return prolist;
    }
    public void setProlist(List<Program_List> prolist) {
        this.prolist = prolist;
    }
    public String getBroadtimes() {
        return broadtimes;
    }
    public void setBroadtimes(String broadtimes) {
        this.broadtimes = broadtimes;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
}
