package com.sixin.system.domain;


import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户消息表对象 user_message
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
public class UserMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 发出消息的人 */
    @Excel(name = "发出消息的人")
    private Long notifier;

    /** 接收消息的人 */
    @Excel(name = "接收消息的人")
    private Long receiver;
    @Excel(name = "接收消息的人姓名")
    private String receiver_name;
    /** 信息所对应的Id，如消息的Id。做外键 */
    @Excel(name = "信息所对应的Id")
    private Long outerid;

    /** 消息的类型：收到通知，任务，系统通知等 */
    @Excel(name = "消息的类型")
    private Long type;

    /** null */
    @Excel(name = "创建时间")
    private String timeCreate;

    /** 0：未读；1：已读 */
    @Excel(name = "0：未读；1：已读")
    private Integer status;

    /** null */
    @Excel(name = "通知人")
    private String notifierName;

    /** null */
    @Excel(name = "通知标题")
    private String outerTitle;

    public void setId(Long id) 
    {
        this.id = id;
    }


    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public Long getId()
    {
        return id;
    }
    public void setNotifier(Long notifier) 
    {
        this.notifier = notifier;
    }

    public Long getNotifier() 
    {
        return notifier;
    }
    public void setReceiver(Long receiver) 
    {
        this.receiver = receiver;
    }

    public Long getReceiver() 
    {
        return receiver;
    }
    public void setOuterid(Long outerid) 
    {
        this.outerid = outerid;
    }

    public Long getOuterid() 
    {
        return outerid;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setTimeCreate(String timeCreate)
    {
        this.timeCreate = timeCreate;
    }

    public String getTimeCreate()
    {
        return timeCreate;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setNotifierName(String notifierName) 
    {
        this.notifierName = notifierName;
    }

    public String getNotifierName() 
    {
        return notifierName;
    }
    public void setOuterTitle(String outerTitle) 
    {
        this.outerTitle = outerTitle;
    }

    public String getOuterTitle() 
    {
        return outerTitle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("notifier", getNotifier())
            .append("receiver", getReceiver())
            .append("outerid", getOuterid())
            .append("type", getType())
            .append("timeCreate", getTimeCreate())
            .append("status", getStatus())
            .append("notifierName", getNotifierName())
            .append("outerTitle", getOuterTitle())
            .toString();
    }
}
