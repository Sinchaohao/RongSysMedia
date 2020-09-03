package com.sixin.iot.domain;

/**
 * @function:定义安防类型表
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-02-15 18:14
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */

import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Securitype extends BaseEntity {
    private int id;    //编号
    private String name;  //名称
    private String demo;   //备注
    private String delflag;  //是否有效

    public Securitype(){

    }

    public Securitype(int id, String name){
        this.id=id;
        this.name=name;
    }

    public Securitype(int id, String name, String demo, String delflag){
        this.id=id;
        this.name=name;
        this.demo=demo;
        this.delflag=delflag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("demo", getDemo())
                .append("delflag", getDelflag())
                .toString();
    }
}
