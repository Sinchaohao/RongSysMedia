package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.PersonalDetailMapper;
import com.sixin.village.domain.PersonalDetail;
import com.sixin.village.service.IPersonalDetailService;
import com.sixin.common.support.Convert;

/**
 * 个人详细 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
@Service
public class PersonalDetailServiceImpl implements IPersonalDetailService 
{
	@Autowired
	private PersonalDetailMapper personalDetailMapper;

	/**
     * 查询个人详细信息
     * 
     * @param pdid 个人详细ID
     * @return 个人详细信息
     */
    @Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public PersonalDetail selectPersonalDetailById(Integer pdid)
	{
	    return personalDetailMapper.selectPersonalDetailById(pdid);
	}
	
	/**
     * 查询个人详细列表
     * 
     * @param personalDetail 个人详细信息
     * @return 个人详细集合
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public List<PersonalDetail> selectPersonalDetailList(PersonalDetail personalDetail)
	{
	    return personalDetailMapper.selectPersonalDetailList(personalDetail);
	}
	
    /**
     * 新增个人详细
     * 
     * @param personalDetail 个人详细信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int insertPersonalDetail(PersonalDetail personalDetail)
	{
	    return personalDetailMapper.insertPersonalDetail(personalDetail);
	}
	
	/**
     * 修改个人详细
     * 
     * @param personalDetail 个人详细信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int updatePersonalDetail(PersonalDetail personalDetail)
	{
	    return personalDetailMapper.updatePersonalDetail(personalDetail);
	}

	/**
     * 删除个人详细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int deletePersonalDetailByIds(String ids)
	{
		return personalDetailMapper.deletePersonalDetailByIds(Convert.toStrArray(ids));
	}
	
}
