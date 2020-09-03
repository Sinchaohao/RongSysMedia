package com.sixin.village.mapper;

import com.sixin.village.domain.HouseMemberInfo;
import java.util.List;	

/**
 * 村户 数据层
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public interface HouseMemberInfoMapper 
{
	/**
     * 查询村户信息
     * 
     * @param hmid 村户ID
     * @return 村户信息
     */
	public HouseMemberInfo selectHouseMemberInfoById(Integer hmid);
	
	/**
     * 查询村户列表
     * 
     * @param houseMemberInfo 村户信息
     * @return 村户集合
     */
	public List<HouseMemberInfo> selectHouseMemberInfoList(HouseMemberInfo houseMemberInfo);
	
	/**
     * 新增村户
     * 
     * @param houseMemberInfo 村户信息
     * @return 结果
     */
	public int insertHouseMemberInfo(HouseMemberInfo houseMemberInfo);
	
	/**
     * 修改村户
     * 
     * @param houseMemberInfo 村户信息
     * @return 结果
     */
	public int updateHouseMemberInfo(HouseMemberInfo houseMemberInfo);
	
	/**
     * 删除村户
     * 
     * @param hmid 村户ID
     * @return 结果
     */
	public int deleteHouseMemberInfoById(Integer hmid);
	
	/**
     * 批量删除村户
     * 
     * @param hmids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHouseMemberInfoByIds(String[] hmids);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 22:22
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.HouseMemberInfo>
	 */
	public List<HouseMemberInfo> selectHouseMemberInfoByIds(List<String> id);
}