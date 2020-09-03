package com.sixin.system.domain;


import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户消息内容对象 user_message_content
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
public class UserMessageContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息编号 */
    private Long id;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String content;

    /** 是否有效，1有效，0无效 */
    @Excel(name = "是否有效，1有效，0无效")
    private String enable;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setEnable(String enable) 
    {
        this.enable = enable;
    }

    public String getEnable() 
    {
        return enable;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("enable", getEnable())
            .toString();
    }
}
