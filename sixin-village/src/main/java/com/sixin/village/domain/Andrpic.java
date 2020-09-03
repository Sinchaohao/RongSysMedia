package com.sixin.village.domain;
/**
 * @function:三级页面图片表 tb_andrpic
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-01-28 14:40
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import com.sixin.common.base.BaseEntity;

public class Andrpic extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 页面主题编号 */
    private Integer aid;
    /** 三级页面主题 */
    private String tname;
    /** 图片*/
    private String pic;
    /** 创建时间 */
    private String creadate;
    /** 信息状态  */
    private String remark;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCreadate() {
        return creadate;
    }

    public void setCreadate(String creadate) {
        this.creadate = creadate;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Andrpic{" +
                "aid=" + aid +
                ", tname='" + tname + '\'' +
                ", pic='" + pic + '\'' +
                ", creadate=" + creadate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
