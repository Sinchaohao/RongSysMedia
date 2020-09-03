package com.sixin.village.service;

import com.sixin.village.domain.Partyscore;
import java.util.List;

/**
 * 党员自评 服务层
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
public interface IPartyscoreService 
{
	/**
     * 查询党员自评信息
     * 
     * @param sfid 党员自评ID
     * @return 党员自评信息
     */
	public Partyscore selectPartyscoreById(Integer sfid);
	
	/**
     * 查询党员自评列表
     * 
     * @param partyscore 党员自评信息
     * @return 党员自评集合
     */
	public List<Partyscore> selectPartyscoreList(Partyscore partyscore);
	
	/**
     * 新增党员自评
     * 
     * @param partyscore 党员自评信息
     * @return 结果
     */
	public int insertPartyscore(Partyscore partyscore);
	
	/**
     * 修改党员自评
     * 
     * @param partyscore 党员自评信息
     * @return 结果
     */
	public int updatePartyscore(Partyscore partyscore);
		
	/**
     * 删除党员自评信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePartyscoreByIds(String ids);
	
}
