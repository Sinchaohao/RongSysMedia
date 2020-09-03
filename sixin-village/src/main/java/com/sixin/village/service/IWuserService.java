package com.sixin.village.service;

import com.sixin.village.domain.Villageuser;
import com.sixin.village.domain.Wuser;
import java.util.List;

/**
 * 用户基本 服务层
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
public interface IWuserService 
{
	/**
     * 查询用户基本信息
     * 
     * @param uid 用户基本ID
     * @return 用户基本信息
     */
	public Wuser selectWuserById(Integer uid);
	
	/**
     * 查询用户基本列表
     * 
     * @param wuser 用户基本信息
     * @return 用户基本集合
     */
	public List<Wuser> selectWuserList(Wuser wuser);
	
	/**
     * 新增微信用户基本
     * 
     * @param wuser 用户基本信息
     * @return 结果
     */
	public int insertWuser(Wuser wuser);
	
	/**
     * 修改用户基本
     * 
     * @param wuser 用户基本信息
     * @return 结果
     */
	public int updateWuser(Wuser wuser);
		
	/**
     * 删除用户基本信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWuserByIds(String ids);

	/**
	 * 新增平台用户基本
	 *
	 * @param villageuser 用户基本信息
	 * @return 结果
	 */
	public int insertVillageuser(Villageuser villageuser);
}
