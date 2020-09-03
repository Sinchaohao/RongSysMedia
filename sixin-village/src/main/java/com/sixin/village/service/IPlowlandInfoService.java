package com.sixin.village.service;

import com.sixin.village.domain.PlowlandInfo;
import java.util.List;

/**
 * 耕地 服务层
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public interface IPlowlandInfoService 
{
	/**
     * 查询耕地信息
     * 
     * @param pid 耕地ID
     * @return 耕地信息
     */
	public PlowlandInfo selectPlowlandInfoById(Integer pid);
	
	/**
     * 查询耕地列表
     * 
     * @param plowlandInfo 耕地信息
     * @return 耕地集合
     */
	public List<PlowlandInfo> selectPlowlandInfoList(PlowlandInfo plowlandInfo);
	
	/**
     * 新增耕地
     * 
     * @param plowlandInfo 耕地信息
     * @return 结果
     */
	public int insertPlowlandInfo(PlowlandInfo plowlandInfo);
	
	/**
     * 修改耕地
     * 
     * @param plowlandInfo 耕地信息
     * @return 结果
     */
	public int updatePlowlandInfo(PlowlandInfo plowlandInfo);
		
	/**
     * 删除耕地信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePlowlandInfoByIds(String ids);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 23:00
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.PlowlandInfo>
	 */
	public List<PlowlandInfo> selectPlowlandInfoByIds(List<String> id);
	
}
