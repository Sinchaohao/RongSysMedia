package com.sixin.village.mapper;

import com.sixin.village.domain.ForestlandInfo;
import java.util.List;	

/**
 * 林地 数据层
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public interface ForestlandInfoMapper 
{
	/**
     * 查询林地信息
     * 
     * @param fid 林地ID
     * @return 林地信息
     */
	public ForestlandInfo selectForestlandInfoById(Integer fid);
	
	/**
     * 查询林地列表
     * 
     * @param forestlandInfo 林地信息
     * @return 林地集合
     */
	public List<ForestlandInfo> selectForestlandInfoList(ForestlandInfo forestlandInfo);
	
	/**
     * 新增林地
     * 
     * @param forestlandInfo 林地信息
     * @return 结果
     */
	public int insertForestlandInfo(ForestlandInfo forestlandInfo);
	
	/**
     * 修改林地
     * 
     * @param forestlandInfo 林地信息
     * @return 结果
     */
	public int updateForestlandInfo(ForestlandInfo forestlandInfo);
	
	/**
     * 删除林地
     * 
     * @param fid 林地ID
     * @return 结果
     */
	public int deleteForestlandInfoById(Integer fid);
	
	/**
     * 批量删除林地
     * 
     * @param fids 需要删除的数据ID
     * @return 结果
     */
	public int deleteForestlandInfoByIds(String[] fids);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 21:34
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.ForestlandInfo>
	 */
	public List<ForestlandInfo> selectForestlandByIds(List<String> id);
	
}