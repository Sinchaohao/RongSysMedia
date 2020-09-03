package com.sixin.village.domain;

import com.sixin.common.base.BaseEntity;

/**
 * @program: lyb_RongSys
 * @description: 暮云圈评论表
 * @author: Mr.Liu
 * @create: 2019-01-17 15:42
 **/
public class SocialcircleComment extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**评论表id*/
    private String coid;
    /**暮云圈编号*/
    private String pcid;
    /**回复某评论的coid*/
    private String replyid;
    /**评论人id*/
    private int uid;
    /**评论内容*/
    private String ccontent;
    /**是否为敏感评论 是为1 否为0*/
    private String issensitive;
    /**评论日期*/
    private String cdate;
    /**用户所属地区村社区 aid*/
    private String aid;
    /**评论目标用户id*/
    private String to_uid;

    @Override
    public String toString() {
        return "SocialCircleComment{" +
                "coid='" + coid + '\'' +
                ", pcid='" + pcid + '\'' +
                ", replyid='" + replyid + '\'' +
                ", uid=" + uid +
                ", ccontent='" + ccontent + '\'' +
                ", issensitive='" + issensitive + '\'' +
                ", cdate='" + cdate + '\'' +
                ", aid='" + aid + '\'' +
                ", to_uid='" + to_uid + '\'' +
                '}';
    }

    public SocialcircleComment() {
    }

    public SocialcircleComment(String coid, String pcid, String replyid,
                               int uid, String ccontent, String issensitive,
                               String cdate, String aid, String to_uid) {
        this.coid = coid;
        this.pcid = pcid;
        this.replyid = replyid;
        this.uid = uid;
        this.ccontent = ccontent;
        this.issensitive = issensitive;
        this.cdate = cdate;
        this.aid = aid;
        this.to_uid = to_uid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCoid() {
        return coid;
    }

    public void setCoid(String coid) {
        this.coid = coid;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public String getReplyid() {
        return replyid;
    }

    public void setReplyid(String replyid) {
        this.replyid = replyid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public String getIssensitive() {
        return issensitive;
    }

    public void setIssensitive(String issensitive) {
        this.issensitive = issensitive;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
    }
}
