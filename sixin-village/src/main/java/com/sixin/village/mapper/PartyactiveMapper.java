package com.sixin.village.mapper;

import com.sixin.village.domain.Partyactive;
import java.util.List;	

/**
 * 党员活动 数据层
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
public interface PartyactiveMapper 
{
	/**
     * 查询党员活动信息
     * 
     * @param mid 党员活动ID
     * @return 党员活动信息
     */
	public Partyactive selectPartyactiveById(Integer mid);
	
	/**
     * 查询党员活动列表
     * 
     * @param partyactive 党员活动信息
     * @return 党员活动集合
     */
	public List<Partyactive> selectPartyactiveList(Partyactive partyactive);
	
	/**
     * 新增党员活动
     * 
     * @param partyactive 党员活动信息
     * @return 结果
     */
	public int insertPartyactive(Partyactive partyactive);
	
	/**
     * 修改党员活动
     * 
     * @param partyactive 党员活动信息
     * @return 结果
     */
	public int updatePartyactive(Partyactive partyactive);
	
	/**
     * 删除党员活动
     * 
     * @param mid 党员活动ID
     * @return 结果
     */
	public int deletePartyactiveById(Integer mid);
	
	/**
     * 批量删除党员活动
     * 
     * @param mids 需要删除的数据ID
     * @return 结果
     */
	public int deletePartyactiveByIds(String[] mids);
	
}