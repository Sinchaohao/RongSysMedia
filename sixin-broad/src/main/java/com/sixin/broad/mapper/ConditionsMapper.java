package com.sixin.broad.mapper;

import com.sixin.broad.domain.Conditions;
import java.util.List;	

/**
 * 设备报 数据层
 * 
 * @author 张超
 * @date 2019-01-15
 */
public interface ConditionsMapper 
{
	/**
     * 查询设备报信息
     * 
     * @param tid 设备报ID
     * @return 设备报信息
     */
	public Conditions selectConditionsById(String tid);
	
	/**
     * 查询设备报列表
     * 
     * @param conditions 设备报信息
     * @return 设备报集合
     */
	public List<Conditions> selectConditionsList(Conditions conditions);
	
	/**
     * 新增设备报
     * 
     * @param conditions 设备报信息
     * @return 结果
     */
	public int insertConditions(Conditions conditions);
	
	/**
     * 修改设备报
     * 
     * @param conditions 设备报信息
     * @return 结果
     */
	public int updateConditions(Conditions conditions);
	
	/**
     * 删除设备报
     * 
     * @param tid 设备报ID
     * @return 结果
     */
	public int deleteConditionsById(String tid);
	
	/**
     * 批量删除设备报
     * 
     * @param tids 需要删除的数据ID
     * @return 结果
     */
	public int deleteConditionsByIds(String[] tids);

	/**
	 * 随机选取100个数据
	 *
	 * @param
	 * @return 结果
	 */
	public List<Conditions> selectConditionsByRandom();
}