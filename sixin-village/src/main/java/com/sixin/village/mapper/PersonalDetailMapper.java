package com.sixin.village.mapper;

import com.sixin.village.domain.PersonalDetail;
import java.util.List;	

/**
 * 个人详细 数据层
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public interface PersonalDetailMapper 
{
	/**
     * 查询个人详细信息
     * 
     * @param pdid 个人详细ID
     * @return 个人详细信息
     */
	public PersonalDetail selectPersonalDetailById(Integer pdid);
	
	/**
     * 查询个人详细列表
     * 
     * @param personalDetail 个人详细信息
     * @return 个人详细集合
     */
	public List<PersonalDetail> selectPersonalDetailList(PersonalDetail personalDetail);
	
	/**
     * 新增个人详细
     * 
     * @param personalDetail 个人详细信息
     * @return 结果
     */
	public int insertPersonalDetail(PersonalDetail personalDetail);
	
	/**
     * 修改个人详细
     * 
     * @param personalDetail 个人详细信息
     * @return 结果
     */
	public int updatePersonalDetail(PersonalDetail personalDetail);
	
	/**
     * 删除个人详细
     * 
     * @param pdid 个人详细ID
     * @return 结果
     */
	public int deletePersonalDetailById(Integer pdid);
	
	/**
     * 批量删除个人详细
     * 
     * @param pdids 需要删除的数据ID
     * @return 结果
     */
	public int deletePersonalDetailByIds(String[] pdids);
	
}