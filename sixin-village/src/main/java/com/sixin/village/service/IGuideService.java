package com.sixin.village.service;

import com.sixin.village.domain.Guide;
import java.util.List;

/**
 * 常用办事指南 服务层
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
public interface IGuideService 
{
	/**
     * 查询常用办事指南信息
     * 
     * @param guid 常用办事指南ID
     * @return 常用办事指南信息
     */
	public Guide selectGuideById(Integer guid);
	
	/**
     * 查询常用办事指南列表
     * 
     * @param guide 常用办事指南信息
     * @return 常用办事指南集合
     */
	public List<Guide> selectGuideList(Guide guide);
	
	/**
     * 新增常用办事指南
     * 
     * @param guide 常用办事指南信息
     * @return 结果
     */
	public int insertGuide(Guide guide);
	
	/**
     * 修改常用办事指南
     * 
     * @param guide 常用办事指南信息
     * @return 结果
     */
	public int updateGuide(Guide guide);
		
	/**
     * 删除常用办事指南信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGuideByIds(String ids);
	
}
