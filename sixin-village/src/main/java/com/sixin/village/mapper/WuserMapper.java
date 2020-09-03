package com.sixin.village.mapper;

import com.sixin.village.domain.Villageuser;
import com.sixin.village.domain.Wuser;
import java.util.List;	

/**
 * 用户基本 数据层
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
public interface WuserMapper 
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
     * 新增用户基本
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
     * 删除用户基本
     * 
     * @param uid 用户基本ID
     * @return 结果
     */
	public int deleteWuserById(Integer uid);
	
	/**
     * 批量删除用户基本
     * 
     * @param uids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWuserByIds(String[] uids);

	/**
	 * 新增平台用户基本
	 *
	 * @param villageuser 用户基本信息
	 * @return 结果
	 */
	public int insertVillageuser(Villageuser villageuser);

}