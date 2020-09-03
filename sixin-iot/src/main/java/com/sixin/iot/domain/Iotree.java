package com.sixin.iot.domain;

import com.sixin.common.base.BaseEntity;
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
public class Iotree extends BaseEntity {

    private Long dept_id;
    private Long parent_id;
    private String imei;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("dept_id", getDept_id())
                .append("parent_id", getParent_id())
                .append("imei", getImei())
                .toString();
    }
    public Long getDept_id() {
        return dept_id;
    }

    public void setDept_id(Long dept_id) {
        this.dept_id = dept_id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
