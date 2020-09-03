package com.sixin.village.service;

import com.sixin.village.domain.Partyaffairs;
import java.util.List;

/**
 * 党务公开 服务层
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
public interface IPartyaffairsService 
{
	/**
     * 查询党务公开信息
     * 
     * @param dwid 党务公开ID
     * @return 党务公开信息
     */
	public Partyaffairs selectPartyaffairsById(Integer dwid);
	
	/**
     * 查询党务公开列表
     * 
     * @param partyaffairs 党务公开信息
     * @return 党务公开集合
     */
	public List<Partyaffairs> selectPartyaffairsList(Partyaffairs partyaffairs);
	
	/**
     * 新增党务公开
     * 
     * @param partyaffairs 党务公开信息
     * @return 结果
     */
	public int insertPartyaffairs(Partyaffairs partyaffairs);
	
	/**
     * 修改党务公开
     * 
     * @param partyaffairs 党务公开信息
     * @return 结果
     */
	public int updatePartyaffairs(Partyaffairs partyaffairs);
		
	/**
     * 删除党务公开信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePartyaffairsByIds(String ids);
	
}
