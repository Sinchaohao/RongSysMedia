package com.sixin.broad.domain;

/**
 * 短信播出端
 *
 * @author 周博
 * @date 2019-03-31
 */
public class Users {
    private String userid; //用户编号
    private String userlevel;//账号级别，只有系统管理账号有此属性
    private String username;//登录名
    private String password;//登录密码
    private String pwd;//登录密码
    private String uname; //姓名
    private String uphone;//联系电话
    private String uaddress; //住址
    private String note;//备注
    private String aid;//区域编号
    private String rid;//角色编号
    private Boolean isuse;//是否可用
    private String lastdate;

    private String aname;//区域名称
    private String rname;//角色名称
    private String roletype;
    private String menurights;
    private String buttonrights;
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUphone() {
        return uphone;
    }
    public void setUphone(String uphone) {
        this.uphone = uphone;
    }
    public String getUaddress() {
        return uaddress;
    }
    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getAid() {
        return aid;
    }
    public void setAid(String aid) {
        this.aid = aid;
    }
    public String getRid() {
        return rid;
    }
    public void setRid(String rid) {
        this.rid = rid;
    }
    public Boolean getIsuse() {
        return isuse;
    }
    public void setIsuse(Boolean isuse) {
        this.isuse = isuse;
    }
    public String getLastdate() {
        return lastdate;
    }
    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }
    public String getAname() {
        return aname;
    }
    public void setAname(String aname) {
        this.aname = aname;
    }
    public String getRname() {
        return rname;
    }
    public void setRname(String rname) {
        this.rname = rname;
    }
    public String getRoletype() {
        return roletype;
    }
    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }
    public String getMenurights() {
        return menurights;
    }
    public void setMenurights(String menurights) {
        this.menurights = menurights;
    }
    public String getButtonrights() {
        return buttonrights;
    }
    public void setButtonrights(String buttonrights) {
        this.buttonrights = buttonrights;
    }
    public String getUserlevel() {
        return userlevel;
    }
    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
