package com.sixin.village.service;

import com.sixin.village.domain.PersonalDetail;
import java.util.List;

/**
 * 个人详细 服务层
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public interface IPersonalDetailService 
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
     * 删除个人详细信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePersonalDetailByIds(String ids);
	
}
