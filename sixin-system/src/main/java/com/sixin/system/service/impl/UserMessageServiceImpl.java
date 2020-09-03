package com.sixin.system.service.impl;

import java.util.List;

import com.sixin.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.system.mapper.UserMessageMapper;
import com.sixin.system.domain.UserMessage;
import com.sixin.system.service.IUserMessageService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户消息表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
@Service
public class UserMessageServiceImpl implements IUserMessageService 
{
    @Autowired
    private UserMessageMapper userMessageMapper;

    /**
     * 查询用户消息表
     * 
     * @param id 用户消息表ID
     * @return 用户消息表
     */
    @Override
    public UserMessage selectUserMessageById(Long id)
    {
        return userMessageMapper.selectUserMessageById(id);
    }

    @Override
    public List<UserMessage> selectEnergyListByids(List<Long> list) {
        return userMessageMapper.selectEnergyListByids(list);
    }

    /**
     * 查询用户消息表列表
     * 
     * @param userMessage 用户消息表
     * @return 用户消息表
     */
    @Override
    public List<UserMessage> selectUserMessageList(UserMessage userMessage)
    {
        return userMessageMapper.selectUserMessageList(userMessage);
    }

    /**
     * 新增用户消息表
     * 
     * @param userMessage 用户消息表
     * @return 结果
     */
    @Override
    public int insertUserMessage(UserMessage userMessage)
    {
        return userMessageMapper.insertUserMessage(userMessage);
    }

    @Override
    @Transactional
    public int insertUserMessageList(List<UserMessage> userMessageList) {
        return userMessageMapper.insertUserMessageList(userMessageList);
    }

    /**
     * 修改用户消息表
     * 
     * @param userMessage 用户消息表
     * @return 结果
     */
    @Override
    public int updateUserMessage(UserMessage userMessage)
    {
        return userMessageMapper.updateUserMessage(userMessage);
    }

    /**
     * 删除用户消息表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserMessageByIds(String ids)
    {
        return userMessageMapper.deleteUserMessageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户消息表信息
     * 
     * @param id 用户消息表ID
     * @return 结果
     */
    @Override
    public int deleteUserMessageById(Long id)
    {
        return userMessageMapper.deleteUserMessageById(id);
    }
}
