package com.sixin.system.service;

import com.sixin.system.domain.UserMessageContent;

import java.util.List;

/**
 * 用户消息内容Service接口
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
public interface IUserMessageContentService 
{
    /**
     * 查询用户消息内容
     * 
     * @param id 用户消息内容ID
     * @return 用户消息内容
     */
    public UserMessageContent selectUserMessageContentById(Long id);

    /**
     * 查询用户消息内容列表
     * 
     * @param userMessageContent 用户消息内容
     * @return 用户消息内容集合
     */
    public List<UserMessageContent> selectUserMessageContentList(UserMessageContent userMessageContent);

    /**
     * 新增用户消息内容
     * 
     * @param userMessageContent 用户消息内容
     * @return 结果
     */
    public int insertUserMessageContent(UserMessageContent userMessageContent);

    /**
     * 修改用户消息内容
     * 
     * @param userMessageContent 用户消息内容
     * @return 结果
     */
    public int updateUserMessageContent(UserMessageContent userMessageContent);

    /**
     * 批量删除用户消息内容
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserMessageContentByIds(String ids);

    /**
     * 删除用户消息内容信息
     * 
     * @param id 用户消息内容ID
     * @return 结果
     */
    public int deleteUserMessageContentById(Long id);
}
