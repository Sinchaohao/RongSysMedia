package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.PartyaffairsMapper;
import com.sixin.village.domain.Partyaffairs;
import com.sixin.village.service.IPartyaffairsService;
import com.sixin.common.support.Convert;

/**
 * 党务公开 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
@Service
public class PartyaffairsServiceImpl implements IPartyaffairsService 
{
	@Autowired
	private PartyaffairsMapper partyaffairsMapper;

	/**
     * 查询党务公开信息
     * 
     * @param dwid 党务公开ID
     * @return 党务公开信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Partyaffairs selectPartyaffairsById(Integer dwid)
	{
	    return partyaffairsMapper.selectPartyaffairsById(dwid);
	}
	
	/**
     * 查询党务公开列表
     * 
     * @param partyaffairs 党务公开信息
     * @return 党务公开集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partyaffairs> selectPartyaffairsList(Partyaffairs partyaffairs)
	{
	    return partyaffairsMapper.selectPartyaffairsList(partyaffairs);
	}
	
    /**
     * 新增党务公开
     * 
     * @param partyaffairs 党务公开信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertPartyaffairs(Partyaffairs partyaffairs)
	{
	    return partyaffairsMapper.insertPartyaffairs(partyaffairs);
	}
	
	/**
     * 修改党务公开
     * 
     * @param partyaffairs 党务公开信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updatePartyaffairs(Partyaffairs partyaffairs)
	{
	    return partyaffairsMapper.updatePartyaffairs(partyaffairs);
	}

	/**
     * 删除党务公开对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deletePartyaffairsByIds(String ids)
	{
		return partyaffairsMapper.deletePartyaffairsByIds(Convert.toStrArray(ids));
	}
	
}
