package com.sixin.system.mapper;

import com.sixin.system.domain.UserMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户消息表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-15
 */
public interface UserMessageMapper 
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
    public List<UserMessage> selectEnergyListByids(@Param("list") List<Long> list);

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
     * @param list 用户消息表
     * @return 结果
     */
    public int insertUserMessageList(@Param("list") List<UserMessage> list);

    /**
     * 修改用户消息表
     * 
     * @param userMessage 用户消息表
     * @return 结果
     */
    public int updateUserMessage(UserMessage userMessage);

    /**
     * 删除用户消息表
     * 
     * @param id 用户消息表ID
     * @return 结果
     */
    public int deleteUserMessageById(Long id);

    /**
     * 批量删除用户消息表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserMessageByIds(String[] ids);
}
