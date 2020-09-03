package com.sixin.system.mapper;

import com.sixin.system.domain.UserMessageContent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户消息内容Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
public interface UserMessageContentMapper 
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
    @Transactional
    public int insertUserMessageContent(UserMessageContent userMessageContent);

    /**
     * 修改用户消息内容
     * 
     * @param userMessageContent 用户消息内容
     * @return 结果
     */
    public int updateUserMessageContent(UserMessageContent userMessageContent);

    /**
     * 删除用户消息内容
     * 
     * @param id 用户消息内容ID
     * @return 结果
     */
    public int deleteUserMessageContentById(Long id);

    /**
     * 批量删除用户消息内容
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserMessageContentByIds(String[] ids);
}
