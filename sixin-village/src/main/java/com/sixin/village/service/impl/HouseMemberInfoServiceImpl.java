package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.HouseMemberInfoMapper;
import com.sixin.village.domain.HouseMemberInfo;
import com.sixin.village.service.IHouseMemberInfoService;
import com.sixin.common.support.Convert;

/**
 * 村户 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
@Service
public class HouseMemberInfoServiceImpl implements IHouseMemberInfoService 
{
	@Autowired
	private HouseMemberInfoMapper houseMemberInfoMapper;

	/**
     * 查询村户信息
     * 
     * @param hmid 村户ID
     * @return 村户信息
     */
    @Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public HouseMemberInfo selectHouseMemberInfoById(Integer hmid)
	{
	    return houseMemberInfoMapper.selectHouseMemberInfoById(hmid);
	}
	
	/**
     * 查询村户列表
     * 
     * @param houseMemberInfo 村户信息
     * @return 村户集合
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public List<HouseMemberInfo> selectHouseMemberInfoList(HouseMemberInfo houseMemberInfo)
	{
	    return houseMemberInfoMapper.selectHouseMemberInfoList(houseMemberInfo);
	}
	
    /**
     * 新增村户
     * 
     * @param houseMemberInfo 村户信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int insertHouseMemberInfo(HouseMemberInfo houseMemberInfo)
	{
	    return houseMemberInfoMapper.insertHouseMemberInfo(houseMemberInfo);
	}
	
	/**
     * 修改村户
     * 
     * @param houseMemberInfo 村户信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int updateHouseMemberInfo(HouseMemberInfo houseMemberInfo)
	{
	    return houseMemberInfoMapper.updateHouseMemberInfo(houseMemberInfo);
	}

	/**
     * 删除村户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int deleteHouseMemberInfoByIds(String ids)
	{
		return houseMemberInfoMapper.deleteHouseMemberInfoByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public List<HouseMemberInfo> selectHouseMemberInfoByIds(List<String> id) {
		return houseMemberInfoMapper.selectHouseMemberInfoByIds(id);
	}

}
