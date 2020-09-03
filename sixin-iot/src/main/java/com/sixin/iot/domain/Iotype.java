package com.sixin.iot.domain;

import com.sixin.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @param
 * @Description:
 * @Return:
 * @Author: 蔡文静
 * @单位：湖南农业大学物联网工程专业
 * @Date:
 * @开发版本：综合练习VO.1
 */
public class Iotype {

    @Excel(name="序号")
    private Integer id;
    @Excel(name="测试类型")
    private String ctype;
    @Excel(name="是否有效")
    private String delflag;




    public Iotype() {

    }
//    public Iotype(Integer id, String ctype){
//        this.id=id;
//        this.ctype=ctype;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("ctype", getCtype())
                .append("delflag", getDelflag())
                .toString();
    }
}
