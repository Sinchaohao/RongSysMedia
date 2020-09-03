package com.sixin.system.service.impl;

import java.util.List;

import com.sixin.common.support.Convert;
import com.sixin.system.domain.UserMessageContent;
import com.sixin.system.mapper.UserMessageContentMapper;
import com.sixin.system.service.IUserMessageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户消息内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
@Service
public class UserMessageContentServiceImpl implements IUserMessageContentService
{
    @Autowired
    private UserMessageContentMapper userMessageContentMapper;

    /**
     * 查询用户消息内容
     * 
     * @param id 用户消息内容ID
     * @return 用户消息内容
     */
    @Override
    public UserMessageContent selectUserMessageContentById(Long id)
    {
        return userMessageContentMapper.selectUserMessageContentById(id);
    }

    /**
     * 查询用户消息内容列表
     * 
     * @param userMessageContent 用户消息内容
     * @return 用户消息内容
     */
    @Override
    public List<UserMessageContent> selectUserMessageContentList(UserMessageContent userMessageContent)
    {
        return userMessageContentMapper.selectUserMessageContentList(userMessageContent);
    }

    /**
     * 新增用户消息内容
     * 
     * @param userMessageContent 用户消息内容
     * @return 结果
     */
    @Override
    public int insertUserMessageContent(UserMessageContent userMessageContent)
    {
        return userMessageContentMapper.insertUserMessageContent(userMessageContent);
    }

    /**
     * 修改用户消息内容
     * 
     * @param userMessageContent 用户消息内容
     * @return 结果
     */
    @Override
    public int updateUserMessageContent(UserMessageContent userMessageContent)
    {
        return userMessageContentMapper.updateUserMessageContent(userMessageContent);
    }

    /**
     * 删除用户消息内容对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserMessageContentByIds(String ids)
    {
        return userMessageContentMapper.deleteUserMessageContentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户消息内容信息
     * 
     * @param id 用户消息内容ID
     * @return 结果
     */
    @Override
    public int deleteUserMessageContentById(Long id)
    {
        return userMessageContentMapper.deleteUserMessageContentById(id);
    }
}
