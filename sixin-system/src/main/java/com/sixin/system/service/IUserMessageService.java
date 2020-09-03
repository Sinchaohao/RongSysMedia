package com.sixin.system.service;

import com.sixin.system.domain.UserMessage;
import org.apache.catalina.User;

import java.util.List;

/**
 * 用户消息表Service接口
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
public interface IUserMessageService 
{
    /**
     * 查询用户消息表
     * 
     * @param id 用户消息表ID
     * @return 用户消息表
     */
    public UserMessage selectUserMessageById(Long id);
    /**
     * 查询用户消息表
     *
     * @param list 用户消息表ID
     * @return 用户消息表
     */
    public List<UserMessage> selectEnergyListByids(List<Long> list);
    /**
     * 查询用户消息表列表
     * 
     * @param userMessage 用户消息表
     * @return 用户消息表集合
     */
    public List<UserMessage> selectUserMessageList(UserMessage userMessage);

    /**
     * 新增用户消息表
     * 
     * @param userMessage 用户消息表
     * @return 结果
     */
    public int insertUserMessage(UserMessage userMessage);
    /**
     * 新增用户消息表
     *
     * @param userMessageList 用户消息表
     * @return 结果
     */
    public int insertUserMessageList(List<UserMessage> userMessageList);
    /**
     * 修改用户消息表
     * 
     * @param userMessage 用户消息表
     * @return 结果
     */
    public int updateUserMessage(UserMessage userMessage);

    /**
     * 批量删除用户消息表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserMessageByIds(String ids);

    /**
     * 删除用户消息表信息
     * 
     * @param id 用户消息表ID
     * @return 结果
     */
    public int deleteUserMessageById(Long id);
}
